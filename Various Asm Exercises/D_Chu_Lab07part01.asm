;Lab 07
;David Chu
;part 01

INCLUDE Irvine32.inc
.data
darr SDWORD 10 DUP(?)
prompt1 BYTE "Enter a lower boundary:", 0
prompt2 BYTE "Enter an upper boundary:", 0
blankline BYTE "  ", 0
lowerB SDWORD ?
upperB SDWORD ?


.code


;create random array (between -100 and 100;)
main PROC
mov ecx, LENGTHOF darr
mov esi, OFFSET darr
L1:
;code to fill array
mov eax, 201
call RandomRange
sub eax, 100
push ecx
sub ecx, 1
mov [esi + ecx * 4], eax
pop ecx
call WriteInt
loop L1
;output the random array
;ask user for the range (mov edx, prompt1; call ReadInt)
mov edx, OFFSET blankline
call writestring
mov edx, OFFSET prompt1
call WriteString ;prompts user for lower boundary
call ReadInt ;returns in eax
;store eax
mov lowerB, eax
mov edx, OFFSET prompt2
call WriteString ;prompts user for upper boundary
call ReadInt ;returns in eax
;store eax
mov upperB, eax
push ebx
push edx
mov ebx, lowerB
mov edx, upperB
mov ecx, LENGTHOF darr
call ArrSumRange
;output
pop edx
pop ebx
push edx
mov edx, OFFSET darr
call displayArray
pop edx

call WriteInt

;ask user for a different range
mov edx, OFFSET prompt1
call WriteString ;prompts user for lower boundary
call ReadInt ;returns in eax
;store eax
mov lowerB, eax
mov edx, OFFSET prompt2
call WriteString ;prompts user for upper boundary
call ReadInt ;returns in eax
;store eax
mov upperB, eax
push ebx
push edx
mov ebx, lowerB
mov edx, upperB
mov ecx, LENGTHOF darr
call ArrSumRange
;output
pop edx
pop ebx
push edx
mov edx, OFFSET darr
call displayArray
pop edx
Call WriteInt
Call WaitMsg
ret
main ENDP


ArrSumRange PROC
;Receives:
;OFFSET array   in esi
;array length   in ecx
;lower boundary in ebx
;upper          in edx (inclusive)
;returns sum    in eax
mov eax, 0
push esi
Lst:
  cmp [esi], ebx
  JL Fin
  cmp [esi], edx
  JGE Fin
  add eax,[esi]
  Fin: add esi, 4
  loop Lst
pop esi
ret
ArrSumRange ENDP


displayArray PROC
;takes in pointer to array in edx
call WriteString
call Crlf
ret
displayArray ENDP


end main