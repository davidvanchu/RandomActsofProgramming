;David Chu
;lab 08
;todo: update to read in string from command line
INCLUDE Irvine32.inc

.data
key BYTE 6, 4, 1, 2, 7, 5, 2, 4, 3, 6
myStr BYTE "Hello, World!", 0
.code
main PROC
mov edx, OFFSET myStr
mov edi, OFFSET key
mov ebx, 0
mov ecx, LENGTHOF myStr
call WriteString
call Crlf
mov ebx, 0
call Crypt
call WriteString
call Crlf
mov ebx, 1
call Crypt
call WriteString
call WaitMsg
ret
main ENDP

Crypt PROC
;Receives:  OFFSET of string in edx
           ;OFFSET of key array in edi
           ;direction (0 or 1) in ebx
           ;0 = encoding rol, 1 = decoding ror
           ;string length in ecx
           ;preserves EAX, EBX, ECX.
push eax
push ecx
mov eax, 10
L1:
	sub eax, 1
	sub ecx, 1
	cmp ebx, 0
	jz encoding
	jnz decoding
	encoding:
	;rol [edx + ecx], [edi + eax]
	push ebx
	mov bl, [edx + ecx]
	push ecx
	mov cl, [edi + eax]
	rol bl, cl
	pop ecx
	mov [edx + ecx], bl
	pop ebx
	jmp keyCheck

	decoding:
	;ror [edx + ecx], [edi + eax]
	push ebx
	mov bl, [edx + ecx]
	push ecx
	mov cl, [edi + eax]
	ror bl, cl
	pop ecx
	mov [edx + ecx], bl
	pop ebx

	keyCheck:
	cmp eax, 0
	jnz looper
	mov eax, 10
	looper:
add ecx, 1
loop L1
pop ecx
pop eax
ret
Crypt ENDP
end main
