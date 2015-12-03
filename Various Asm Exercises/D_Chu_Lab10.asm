;David Chu
;lab 10
INCLUDE Irvine32.inc
.data
testArr BYTE 0,11,11,22,22,22,33,33
testKey BYTE 22

testArr2 BYTE 13,23,33,64,64,64,42,22,55,55
testKey2 BYTE 64

testArr3 BYTE 5,5,5
testKey3 BYTE 5

testArr4 BYTE 12,12,12,3,1,4,46,43,76,99,29,12
testKey4 BYTE 12

testArr5 BYTE 12,12,12,3,1,4,46,43,76,99,29,12
testKey5 BYTE 3


.code
FindTriplets PROC USES ecx esi ebx,
   pArray:PTR BYTE,
   len:BYTE,
   key:BYTE
   ;LOCAL counter:BYTE

mov		eax,0				;eax is counter
movzx	ecx,len
mov		esi,pArray
movzx	ebx,key
L1:
   cmp	[esi + ecx - 1],bl			;compare value[i] to key
   jne	notEqual
   inc	eax					;if equal, increment eax, i.e counter
   jmp	endL1				;skip notEqual
   notEqual:
      mov		eax,0			;reset eax
      endL1:					;check counter
         cmp		eax,3
         je		tripletExists  ;if counter is 3, jump to end of proc
loop L1
tripletDoesntExist:
   mov	eax, 0
   jmp	fin
tripletExists:
   mov	eax, 1
fin:
   RET
FindTriplets ENDP

main PROC
INVOKE FindTriplets, OFFSET testArr, LENGTHOF testArr, testKey
Call WriteDec
Call Crlf

INVOKE FindTriplets, OFFSET testArr2, LENGTHOF testArr2, testKey2
Call WriteDec
Call Crlf

INVOKE FindTriplets, OFFSET testArr3, LENGTHOF testArr3, testKey3
Call WriteDec
Call Crlf

INVOKE FindTriplets, OFFSET testArr4, LENGTHOF testArr4, testKey4
Call WriteDec
Call Crlf

INVOKE FindTriplets, OFFSET testArr5, LENGTHOF testArr5, testKey5
Call WriteDec
Call Crlf


call WaitMsg
Invoke ExitProcess,0
main ENDP



end main
