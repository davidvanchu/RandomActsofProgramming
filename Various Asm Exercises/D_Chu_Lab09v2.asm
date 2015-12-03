;David Chu
;lab 09 v2

INCLUDE Irvine32.inc
.data
grades WORD 10 DUP (?)
weights WORD 1,2,3,4,4,4,4,3,2,1
prompt BYTE "Enter 10 positive numbers between 1 and 200",0
prompt2 BYTE "The average is: ",0
.code
main PROC
;byte al word ax dword eax
   mov edx, OFFSET grades
   mov ecx, LENGTHOF grades
   mov esi, OFFSET weights
   mov eax, OFFSET prompt
   call UsrFillArr
   mov eax, OFFSET prompt2
   call ComputeAverage
	call WaitMsg
	Invoke ExitProcess,0
main ENDP

UsrFillArr PROC ;prompts user to fill array.
                ;length in ecx
                ;prompt in eax
                ;array in edx
enterPrompt EQU eax
gradeArr EQU edx
  push   eax
  push   ecx

  push   edx
  mov    edx, enterPrompt
  call   WriteString
  pop    edx

  push   edx
  L1:
      dec   ecx
      call  ReadDec
      mov   [gradeArr + ecx * 2], ax
      inc   ecx
  loop   L1
  pop    edx
  pop    ecx
  pop    eax
  ret
UsrFillArr ENDP

ComputeAverage PROC ;returns answer in eax.
                    ;Length of array in ECX,
                    ;result prompt in eax
                    ;gradearr in edx
                    ;weightarr in esi
resultPrompt EQU eax
weightArr EQU esi
gradeArr EQU edx
   push  ebx
   push  edx
   push  ecx
   mov   eax, 0
   mov   ebx, 0
   ;store summation arr * weight in EAX,
   ;store summation weight in EBX;
   push ecx
   L2:
      dec   ecx
      push  edx ;save gradeArr pointer
	 mov	  eax, 0
      mov   ax, [gradeArr + ecx * 2]
	 push  ecx
	 add   ecx, weightArr
      mul   ecx
	 pop   ecx
      add   ebx, eax ;store summation in ebx for now
      pop   edx ;store gradeArr pointer
      inc   ecx
   loop L2
   xchg eax, ebx ;summation arr * weight now in eax
   mov ebx, 0
   pop ecx
   push ecx
   L3:
      dec   ecx
      add   bx, WORD PTR [esi + ecx * 2]
      inc   ecx
   loop  L3
   pop   ecx
   div   ebx
   pop   edx
   push  edx
   mov   edx, resultPrompt
   call  WriteString
   call  WriteDec
   pop   edx
   pop   ecx
   pop   ebx
   ret
ComputeAverage ENDP
end main
