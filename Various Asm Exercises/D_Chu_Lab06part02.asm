; Lab 02
; David Chu
; October 08, 2015
; Enhanced DumpMem
INCLUDE Irvine32.inc
.data
myArr WORD 48,83,61,71,354,1231,215,9865,0FFFFh
len = LENGTHOF myArr
.code
main PROC

mov edx,OFFSET myArr
mov ecx,len
call DumpMemSW

call WaitMsg
Invoke	ExitProcess,0
main ENDP

displayArray PROC
;takes in pointer to array in edx
call	WriteString
call Crlf
ret
displayArray ENDP

DumpMemSW PROC
;pointer to WORD array in edx, length in ecx
push ebx
mov	ebx,0		;push ebx to use as counter

DMSWL1:
movzx	eax,WORD PTR [edx + ebx];move first element into eax with zero extend
				;and	eax,0000FFFFh;00000000000000001111111111111111b
call WriteIntSW	;print
add ebx,2			;inc by 2 to move to second element
loop DMSWL1

pop ebx
ret
DumpMemSW ENDP

WriteIntSW PROC
;prints eax to console as int with a space
;push edx
;mov eax,edx
call WriteInt
mov al,32
call WriteChar
;pop edx
ret
WriteIntSW ENDP


end main