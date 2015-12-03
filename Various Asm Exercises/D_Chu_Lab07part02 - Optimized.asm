;Lab 07
;David Chu
;part 02
INCLUDE Irvine32.inc
.data
gradeArr BYTE 10 DUP(?)
.code
	main PROC
	mov ecx, LENGTHOF gradeArr ;create random array (between 50,100)
	mov esi, OFFSET gradeArr
L1:
	mov eax, 51
	call RandomRange
	add eax, 50
	call WriteInt
	push eax
	mov eax, 61		;equals sign ASCII code
	call WriteChar
	pop eax
	call CalcGrade
	call WriteChar		;print grade to screen, grade already in al register
	push ecx			;push counter to modify, use, then restore counter
	sub ecx, 1
	mov [esi + ecx * 4], al
	pop ecx
	call Crlf
loop L1
	call WaitMsg
	ret
main ENDP

CalcGrade PROC
	;receives integer value between 0 and 100 inclusive in eax
	;returns a single capital letter in AL register
	mov ah, al ;copy integer value into ah for comparison
	cmp ah, 90
	mov al, 65 ;Capital A
	JGE Fin
	cmp ah, 80
	mov al, 66 ;B
	JGE Fin
	cmp ah, 70
	mov al, 67 ;C
	JGE Fin
	cmp ah, 60
	mov al, 68 ;D
	JGE Fin
	mov al, 70 ;F
Fin:
	ret
CalcGrade ENDP
end main