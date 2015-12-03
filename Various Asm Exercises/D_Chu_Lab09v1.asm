;David Chu
;lab 09 v 1
INCLUDE Irvine32.inc
.data
xparam SDWORD ?
yparam SDWORD ?
prompt BYTE "Please enter 2 values, x and y.",0
prompt2 BYTE "GCD is: ",0
prompt3 BYTE "A number cannot be 0!",0
.code
main PROC
;byte al word ax dword eax
	mov ecx, 4
l1:
	mov edx, OFFSET prompt
	call WriteString
	call Crlf
	call ReadInt
	mov xparam, eax
	call ReadInt
	mov yparam, eax
	push xparam
	push yparam
	call gcd
	mov edx, OFFSET prompt2
	cmp eax,0
	jnz notzero
	mov edx, OFFSET prompt3
	call WriteString
	jmp ending
notzero:
	call WriteString
	call WriteInt
ending:
	call Crlf
	loop l1
	call WaitMsg
	Invoke ExitProcess,0
main ENDP

gcd PROC ;returns GCD of x and y in EAX. Push x and y values to stack.
y_param EQU [ebp + 12]
x_param EQU [ebp + 8]
  push ebp
  mov ebp, esp
  push ebx
  push ecx
  push edx

  mov eax, 0
  mov ebx, 0
  mov edx, 0
  mov eax, x_param
  mov ebx, y_param
  cmp eax,0
  je zero
  jns checkY ;skip over negate X if positive
negateX:
  neg eax
checkY:
  cmp ebx, 0
  je zero
  jns loop1 ;skip over negate Y if positive
negateY:
  neg ebx
loop1:
  ;div with sqword/sword puts quotient in EAX and remainder in EDX
  ;Edx:Eax = dividend, divisor is operand1
  mov ecx, 0
  div ebx
  mov ecx, edx ;n = x mod y
  mov eax, ebx ;x = y
  mov ebx, ecx ;y = n
  mov edx, 0
  cmp ebx, 0
  je escape
  jmp loop1
zero:
  mov eax, 0
escape:
  pop edx
  pop ecx
  pop ebx
  pop ebp
  ret 8
gcd ENDP
end main
