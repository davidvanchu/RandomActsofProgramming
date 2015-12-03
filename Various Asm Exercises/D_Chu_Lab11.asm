;David Chu
;lab 11
INCLUDE Irvine32.inc
Rectangle STRUCT
   Direction   BYTE 0
   vert1       COORD <?,?>
   vert2       COORD <?,?>
Rectangle ENDS

.data
	testRect	Rectangle < >
	prompt01	BYTE "Enter the coordinates for vert1",0
	prompt10	BYTE "Enter the coordinates for vert2",0
	prompt11	BYTE "Would you like to repeat?",0
	prompt100 BYTE "The direction is ",0
.code
OutputRectangle PROC, rect:Rectangle
	LOCAL dirstr[3]:BYTE

	CheckX:
		mov	ebx,0
		mov	ax,rect.vert1.x
		cmp	ax,rect.vert2.x
		JL	xOK
		xchg	ax,rect.vert2.x
		mov	rect.vert1.x,ax
		add	ebx,1
	xOK:
	CheckY:
		mov	ax,rect.vert1.y
		cmp	ax,rect.vert2.y
		JL	yOK
		xchg	ax,rect.vert2.y
		mov	rect.vert1.y,ax
		add	ebx,2
	yOK:
	CheckDir:
			LEA	edx,dirstr
			cmp	ebx,0
			JNE	N1
			mov	dirstr[0],'N'
			mov	dirstr[1],'E'
			mov	dirstr[2],0
			mov	rect.Direction,0
			jmp	FIN
		N1:
			cmp	ebx,1
			JNE	N2
			mov	dirstr[0],'N'
			mov	dirstr[1],'W'
			mov	dirstr[2],0
			mov	rect.Direction,1
			jmp	FIN
		N2:
			cmp ebx,2
			JNE N3
			mov dirstr[0],'S'
			mov dirstr[1],'E'
			mov dirstr[2],0
			mov rect.Direction,2
		N3:
			cmp ebx,3
			JNE FIN
			mov dirstr[0],'S'
			mov dirstr[1],'W'
			mov dirstr[2],0
			mov rect.Direction,3
		FIN:
		call WriteString
	ret
OutputRectangle ENDP

main PROC
Loop1: 
	mov		edx, OFFSET prompt01
	call		WriteString
	call		Crlf
	call		ReadDec
	mov		testRect.vert1.x,ax
	call		Crlf
	call		ReadDec
	mov		testRect.vert1.y,ax
	call		Crlf

	mov		edx, OFFSET prompt10
	call		WriteString
	call		Crlf
	call		ReadDec
	mov		testRect.vert2.x,ax
	call		Crlf
	call		ReadDec
	mov		testRect.vert2.y,ax
	call		Crlf
	mov		edx,OFFSET prompt100
	call		WriteString
	Invoke	OutputRectangle,testRect
	call		Crlf
	mov		edx, OFFSET prompt11
	call		WriteString ;ask for repeat
	call		Crlf
	call		ReadChar
	cmp		ax,1579h
	je		Loop1
	Invoke	ExitProcess,0
main ENDP

;might implement below later

ReorderRectangle PROC, prect:PTR Rectangle
;invoke reorderrectangle, OFFSET testRect
	mov esi, prect
	mov (Rectangle PTR[esi]).vert1.x,0;
	ret
ReorderRectangle ENDP

end main
