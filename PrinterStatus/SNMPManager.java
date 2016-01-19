import java.io.IOException;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SNMPManager {
	//this is the IP address of the printer, with 161 being the port number
	private final static String IPADDRESSOFPRINTER = "udp:192.168.3.2/161"; //(test class only)
	//Community Key is the password. By default it is "public".
	private final static String COMMUNITYKEY = "public";
	private Snmp snmp = null;
	private String address = null;

	private String sysDesc;
	private String blackToner;
	private String cyanToner;
	private String magentaToner;
	private String yellowToner;
	private String drawer1;

	/** Sets the ip address of the printer, and runs start.
	 * Constructor
	 * @param add
	 * @throws IOException
	 */
	public SNMPManager(String add) throws IOException {
		address = add;
		start();
	}

	public static void main(String[] args) throws IOException {
		/**
		 * Port 161 is used for Read and Other operations
		 * Port 162 is used for the trap generation
		 */
		SNMPManager client = new SNMPManager(IPADDRESSOFPRINTER);
		/**
		 * OID - .1.3.6.1.2.1.1.1.0 => SysDec
		 * OID - .1.3.6.1.2.1.1.5.0 => SysName
		 * => MIB explorer will be useful here, as discussed in previous article
		 */
		String sysDesc = client.getAsString(new OID(".1.3.6.1.2.1.1.5.0"));
		String blackToner = client.getAsString(new OID(".1.3.6.1.2.1.43.11.1.1.9.1.1"));
		String cyanToner = client.getAsString(new OID(".1.3.6.1.2.1.43.11.1.1.9.1.2"));
		String magentaToner = client.getAsString(new OID(".1.3.6.1.2.1.43.11.1.1.9.1.3"));
		String yellowToner = client.getAsString(new OID(".1.3.6.1.2.1.43.11.1.1.9.1.4"));
		String drawer1 = client.getAsString(new OID(".1.3.6.1.2.1.43.8.2.1.10.1.2"));

		System.out.println("\n\n\n");
		System.out.println(sysDesc);
		System.out.println("The current black toner level is " + blackToner + "/100");
		System.out.println("The current cyan toner level is " + cyanToner + "/100");
		System.out.println("The current magenta toner level is " + magentaToner + "/100");
		System.out.println("The current yellow toner level is " + yellowToner + "/100");
		System.out.println("The current paper level (drawer1) is " + drawer1 + "/550");

	}

	/**
	 * Start the Snmp session. If you forget the listen() method you will not
	 * get any answers because the communication is asynchronous
	 * and the listen() method listens for answers.
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void start() throws IOException {
		TransportMapping transport = new DefaultUdpTransportMapping();
		snmp = new Snmp(transport);
		USM usm = new USM(SecurityProtocols.getInstance(),
				new OctetString(MPv3.createLocalEngineID()), 0);
		SecurityModels.getInstance().addSecurityModel(usm);
		transport.listen();

		System.out.println(transport.getListenAddress());
		System.out.println(transport.isListening());
	}

	/**
	 * Method which takes a single OID and returns the response from the agent as a String.
	 * @param oid
	 * @return
	 * @throws IOException
	 */
	private String getAsString(OID oid) throws IOException {
		ResponseEvent event = get(new OID[] { oid });
		if (event.getResponse() != null)
			return event.getResponse().get(0).getVariable().toString();
		else
			System.out.println("Null event returned.");
		return "-1";
	}

	/**
	 * This method is capable of handling multiple OIDs
	 * @param oids
	 * @return
	 * @throws IOException
	 */
	private ResponseEvent get(OID oids[]) throws IOException {
		PDU pdu = new PDU();
		for (OID oid : oids) {
			pdu.add(new VariableBinding(oid));
		}
		pdu.setType(PDU.GET);
		ResponseEvent event = snmp.send(pdu, getTarget(), null);
		if(event != null) {
			return event;
		}
		throw new RuntimeException("GET timed out");
	}

	/**
	 * This method returns a Target, which contains information about
	 * where the data should be fetched and how.
	 * @return
	 * @throws IOException 
	 */
	private Target getTarget() throws IOException {
		Address targetAddress = GenericAddress.parse(address);
		//		System.out.println(targetAddress.isValid());
		//		CommunityTarget target = new CommunityTarget();
		//		target.setCommunity(new OctetString("public"));
		//		target.setAddress(targetAddress);
		//		System.out.println(target.getAddress());
		//		target.setRetries(2);
		//		target.setTimeout(5500);
		//		target.setVersion(SnmpConstants.version2c);
		//		System.out.println(target.toString());
		//		return target;


		CommunityTarget target = new CommunityTarget();
		target.setCommunity(new OctetString(COMMUNITYKEY));
		target.setAddress(targetAddress);
		target.setRetries(2);
		target.setTimeout(100);
		target.setVersion(SnmpConstants.version1);
		// creating PDU
		PDU pdu = new PDU();
		pdu.add(new VariableBinding(new OID(new int[] {1,3,6,1,2,1,1,1})));
		pdu.add(new VariableBinding(new OID(new int[] {1,3,6,1,2,1,1,2})));
		pdu.setType(PDU.GETNEXT);
		// sending request
		ResponseListener listener = new ResponseListener() {
			public void onResponse(ResponseEvent event) {
				// Always cancel async request when response has been received
				// otherwise a memory leak is created! Not canceling a request
				// immediately can be useful when sending a request to a broadcast
				// address.
				((Snmp)event.getSource()).cancel(event.getRequest(), this);
				//System.out.println("Received response PDU is: "+event.getResponse());
			}
		};
		snmp.send(pdu, target, null, listener);
		return target;	
	}

	public void update() throws IOException {
		sysDesc = getAsString(new OID(".1.3.6.1.2.1.1.5.0"));
		blackToner = getAsString(new OID(".1.3.6.1.2.1.43.11.1.1.9.1.1"));
		cyanToner = getAsString(new OID(".1.3.6.1.2.1.43.11.1.1.9.1.2"));
		magentaToner = getAsString(new OID(".1.3.6.1.2.1.43.11.1.1.9.1.3"));
		yellowToner = getAsString(new OID(".1.3.6.1.2.1.43.11.1.1.9.1.4"));
		drawer1 = getAsString(new OID(".1.3.6.1.2.1.43.8.2.1.10.1.2"));
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSysDesc() {
		return sysDesc;
	}

	public String getBlackToner() {
		return blackToner;
	}


	public String getCyanToner() {
		return cyanToner;
	}

	public String getMagentaToner() {
		return magentaToner;
	}

	public String getYellowToner() {
		return yellowToner;
	}

	public String getDrawer1() {
		return drawer1;
	}
}