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
	;code to fill array
	mov eax, 51
	call RandomRange
	add eax, 50
	call CalcGrade
	push ecx
	sub ecx, 1
	mov [esi + ecx * 4], al
	pop ecx
	call WriteInt
	Call CrlF
loop L1
	Call WaitMsg
	ret
main ENDP


CalcGrade PROC
	;receives integer value between 0 and 100 inclusive
	;returns a single capital letter in AL register
	push eax
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
	Call WriteChar
	pop eax
	ret
CalcGrade ENDP
end main