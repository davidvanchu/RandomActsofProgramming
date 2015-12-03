INCLUDE Irvine32.inc
INCLUDE macros.inc

BUFFER_SIZE = 500000

.data
buffer BYTE BUFFER_SIZE DUP(?)
filename BYTE 80 DUP(0)
fileHandle HANDLE ?
outHandle	HANDLE ?
colorAtt WORD 00Ah
fileSize DWORD ?
.code

WriteRow PROTO,
	outH:HANDLE

main PROC
	INVOKE GetStdHandle,STD_OUTPUT_HANDLE
	mov	outHandle,eax
	INVOKE SetConsoleTextAttribute,outHandle,colorAtt
	mWrite "Enter an input filename: "
	mov	edx,OFFSET filename
	mov	ecx,SIZEOF filename
	call ReadString
	
	mov	edx,OFFSET filename
	call OpenInputFile
	mov	fileHandle,eax
	
	cmp	eax,INVALID_HANDLE_VALUE
	jne	file_ok
	mWrite <"Cannot open file",0dh,0ah>
	jmp	quit
file_ok:
	mov	edx,OFFSET buffer
	mov	ecx,BUFFER_SIZE
	call	ReadFromFile
	jnc	check_buffer_size
	mWrite "Error reading file. "
	call	WriteWindowsMsg
	jmp	close_file

check_buffer_size:
	cmp	eax,BUFFER_SIZE
	jb	buf_size_ok
	mWrite <"Error: Buffer too small for the file",0dh,0ah>
	jmp	quit
buf_size_ok:
	mov	buffer[eax],0
	mWrite "File size: "
	call	WriteDec
	call	Crlf

	mWrite <"Buffer:", 0dh,0ah,0dh,0ah>
	mov	edx,OFFSET buffer
	call	WriteString
	call	Crlf

	mov eax,[edx + 02h]
	mov fileSize,eax
	call GetStartingAddress ;eax contains starting address of pixel array

	call DisplayBMP

close_file:
	mov	eax,fileHandle
	call CloseFile
quit:
	call	Crlf
	call WaitMsg
	exit
main ENDP

GetStartingAddress PROC USES edx ;receive offset Buffer in edx
	mWrite <"Beginning address:",0dh,0ah>
	mov	eax,[edx + 0Ah]
	call WriteHex ;writes offset of actual image data
	;mov	eax,[edx + eax]
	call	WriteHex ;writes actual value at offset of image data
	call Crlf
	ret
GetStartingAddress ENDP

WriteRow PROC USES edx,
	outH:HANDLE
	INVOKE SetConsoleTextAttribute,outHandle,al
	mWrite<"A">
	ret
WriteRow ENDP

DisplayBMP PROC ;takes in starting address of pixel array in eax
	mov	ebx,eax ;starting address in ebx now
	;mov eax,50 hardcoded height
	call GetHeight ;HEIGHT IN EAX
	push eax
	jmp	L2
L3:
	call Crlf
L2:
	pop	eax
	dec	eax
	cmp	eax,0
	je	Disfin
	push eax
	;mov eax,79 hardcoded width
	call GetWidth ;width in EAX
	
	L1: ;loop which displays each row
		push eax
		mov	ax,[edx + ebx]
		INVOKE WriteRow,outHandle
		add	ebx,3h
		pop	eax
		dec	eax
		cmp	eax,0
		je	L3
		jmp	L1
Disfin:
ret
DisplayBMP ENDP

GetWidth PROC ;returns width in EAX
	mov	eax,[edx + 12h]
	ret
GetWidth ENDP

GetHeight PROC ;returns height in EAX
	mov eax,[edx + 16h]
	ret
GetHeight ENDP


END main