; Lab 02
; David Chu
; October 08, 2015
INCLUDE Irvine32.inc
.data
myArr BYTE 80 DUP(?)
.code
main PROC
call ReadDec
cmp	eax,80		;limits the length of the random string to 79 characters,
jns	error		;the default console width. Jumps to end if (entered value >=80).

mov	edx,OFFSET myArr
mov	ecx,20		;repeat 20 times as specified in lab
L1:
call	randomStrings
loop L1
error:
call WaitMsg
Invoke	ExitProcess,0
main ENDP

randomStrings PROC ;integer value in eax, pointer to array of bytes in edx
			    ;this procedure generates random string of length L containing
			    ;all capital letters. User is asked for integer value in eax
			    ;using ReadInt.
push ecx

mov	ecx,eax
;mov	ebx,0		originally wanted to fill the array going forward
				;but since it is random anyway, just using ecx as array element
				;pointer
rSL1:
push eax
mov	eax,26
call RandomRange
add	eax,65			;puts random integer into capital letter range
mov	[edx + ecx - 1],al	;moves the resulting letter into the array
pop eax
loop rSL1

call displayArray

pop ecx
ret
randomStrings ENDP

displayArray PROC
;takes in pointer to array in edx
call	WriteString
call Crlf
ret
displayArray ENDP
end main