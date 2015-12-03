;Lab 07
;David Chu
;part 01
;version 2, made test program into separate procedure which is called in main for less
;repeating code and easier readability
;also moved array creation into it's own procedure

INCLUDE Irvine32.inc
.data
darr SDWORD 10 DUP(?)
prompt1 BYTE "Enter a lower boundary:", 0
prompt2 BYTE "Enter an upper boundary:", 0
lowerB SDWORD ?
upperB SDWORD ?

.code
main PROC
mov esi, OFFSET darr
mov ecx, LENGTHOF darr
call CreateArray
push ecx				;push LENGTHOF darr
mov ecx, 2 ;new counter
L2: ;call test program twice
	mov eax, ecx		;preserve counter
	pop ecx			;pop LENGTHOF darr
	mov edx, OFFSET prompt1
	mov ebx, OFFSET prompt2
	push eax			;push counter
	call Tester
	pop eax			;pop counter
	push ecx			;push LENGTHOF darr
	mov ecx, eax		;return counter back to ecx
loop L2
pop ecx				;make the stack happy
call WaitMsg
ret
main ENDP

CreateArray PROC	;populates an array with random numbers between -100 and 100
				;preserves: eax, ecx, esi
				;receives OFFSET array in ESI
				;receives LENGTHOF array in ECX
push eax
push ecx
L1:
	mov eax, 201
	call RandomRange
	sub eax, 100
	push ecx
	sub ecx, 1
	mov [esi + ecx * 4], eax
	pop ecx
	call WriteInt
loop L1
pop ecx
pop eax
ret
CreateArray ENDP

Tester PROC		;asks user for boundaries and calls ArrSumRange to
				;find sum of elements in range of boundaries
				;preserves: eax, ebx, edx, ecx, esi
				;receives prompt1 in edx
				;receives prompt2 in ebx
				;receives LENGTHOF array in ecx
				;receives OFFSET array in esi
push eax
push ebx
push edx
call Crlf	
				;mov edx, OFFSET prompt1 not necessary anymore
call WriteString	;prompts user for lower boundary
call ReadInt		;returns in eax
mov lowerB, eax	;store eax
mov edx, ebx		;mov edx, OFFSET prompt2
call WriteString	;prompts user for upper boundary
call ReadInt		;returns in eax
mov upperB, eax	;store eax
mov ebx, lowerB
mov edx, upperB
				;mov ecx, LENGTHOF darr, was required in version 1
call ArrSumRange
mov edx, esi		;OFFSET darr
call WriteInt
pop edx
pop ebx
pop eax
ret
Tester ENDP

ArrSumRange PROC	;finds sum of elements in array given an upper and lower boundary
				;preserves: esi, ebx, ecx, edx
				;Receives:
				;OFFSET array   in esi
				;array length   in ecx
				;lower boundary in ebx
				;upper boundary in edx
				;returns sum    in eax
mov eax, 0
push esi
push ecx
Lst:
  cmp [esi], ebx
  JL Fin
  cmp [esi], edx
  JG Fin
  add eax,[esi]
  Fin: add esi, 4
  loop Lst
pop ecx
pop esi
ret
ArrSumRange ENDP
end main