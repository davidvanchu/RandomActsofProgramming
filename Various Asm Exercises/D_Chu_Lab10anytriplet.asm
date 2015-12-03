;David Chu
;lab 10 any triplet
INCLUDE Irvine32.inc
.data
testArr BYTE 0,11,11,22,22,22,33,33
testArr2 BYTE 0,23,4,1,2,35,43,34,44,44,44,44,12,1,9
testArr3 BYTE 5,5,5
testArr4 BYTE 2,2,2,3,2
testArr5 BYTE 0,1,2,3,4,5
.code
FindTriplets PROC USES ecx esi, ;returns count in eax, triplet key value in ebx. Returns 0 in eax if not found.
   pArray:PTR BYTE,
   len:BYTE,

mov		eax,1				;eax is counter
movzx	ecx,len
mov		esi,pArray
mov		ebx,0
dec		ecx
mov		bl,[esi + ecx]
L1:
   cmp	[esi + ecx - 1],bl			;compare value[i] to key
   jne	notEqual
   inc	eax					;if equal, increment eax, i.e counter
   jmp	endL1				;skip notEqual
   notEqual:
      mov		eax,1			;reset eax
	 mov		bl,[esi + ecx - 1]
      endL1:					;check counter
         cmp		eax,3
         je		tripletExists  ;if counter is 3, jump to end of proc
loop L1
tripletDoesntExist:
   mov	eax,0
   jmp	fin
tripletExists:
   jmp	fin
fin:
   RET
FindTriplets ENDP

main PROC
INVOKE FindTriplets, OFFSET testArr, LENGTHOF testArr
Call WriteDec
Call Crlf
mov eax,ebx
Call	WriteDec
Call Crlf
INVOKE FindTriplets, OFFSET testArr2, LENGTHOF testArr2
Call WriteDec
Call Crlf
mov eax,ebx
Call	WriteDec
Call Crlf
INVOKE FindTriplets, OFFSET testArr3, LENGTHOF testArr3
Call WriteDec
Call Crlf
mov eax,ebx
Call	WriteDec
Call Crlf
INVOKE FindTriplets, OFFSET testArr4, LENGTHOF testArr4
Call WriteDec
Call Crlf
mov eax,ebx
Call	WriteDec
Call Crlf
INVOKE FindTriplets, OFFSET testArr5, LENGTHOF testArr5
Call WriteDec
Call Crlf
mov eax,ebx
Call	WriteDec
Call Crlf
call WaitMsg
Invoke ExitProcess,0
main ENDP



end main
