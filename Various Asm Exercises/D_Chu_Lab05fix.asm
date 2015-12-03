INCLUDE Irvine32.inc
;Roll array backwards, and output to console after each iteration using dumpmem
;and crlf inside a separate procedure. Call the procedure in main.
.data
myArray	DWORD 000000B3h, 000011C5h, 000022D7h, 000033E8h, 000044F6h,
		000055E4h, 000066D2h
byteLen	= ($ - myArray)
myLen	= LENGTHOF myArray
typeArr	= TYPE myArray
.code
main PROC
mov	edx,OFFSET myArray
mov	ecx,myLen

Lroll:
push	ecx
mov	ecx,myLen
call	rollback
push	esi
push	ecx
push	ebx

mov	esi,OFFSET myArray
mov	ecx,myLen
mov	ebx,typeArr
call	output

pop	ebx
pop	ecx
pop	esi
pop	ecx
loop Lroll

Invoke	ExitProcess,0
main ENDP

rollback PROC ;rolls DWORD array backwards one spot
;array address in edx
;array length in ecx
push ebx
push ecx
push edx

dec	ecx
mov	eax, 0

L1:
mov	ebx, [edx + eax]			;myArray[edx]
xchg	ebx, [edx + eax + 4]		;myArray[edx + sizeArr]
mov	[edx + eax], ebx			;myArray[edx],eax
add	eax, 4					;sizeArr
loop	L1

pop	edx
pop	ecx
pop	ebx
ret
rollback ENDP

output PROC;outputs myArray when called
;memory address of array in esi
;length of array in ecx
;type of array in ebx
call	DumpMem
call	Crlf
ret
output ENDP

end main