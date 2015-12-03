;David Chu
;lab 08
;todo: update to read in string from command line
INCLUDE Irvine32.inc

.data
key BYTE 6, 4, 1, 2, 7, 5, 2, 4, 3, 6
myStr BYTE 51 DUP(0)
strLen DWORD ?
prompt BYTE "Please enter a string to encrypt (NSA approved):",0
originalMsg BYTE " Original: ",0
encryptMsg BYTE "Encrypted: ",0
decryptMsg BYTE "Decrypted: ",0
.code
main PROC
mov edx,OFFSET prompt
call WriteString
mov edx,OFFSET myStr
mov ecx,SIZEOF myStr
call ReadString
mov strLen,eax
mov edi,OFFSET key
mov ebx,0
mov ecx,strLen;LENGTHOF myStr
push edx
mov edx, OFFSET originalMsg
call WriteString
pop edx
call WriteString
call Crlf
mov ebx,0
push edx
mov edx, OFFSET encryptMsg
call WriteString
pop edx
call Crypt
call WriteString
call Crlf
mov ebx,1
push edx
mov edx, OFFSET decryptMsg
call WriteString
pop edx
call Crypt
call WriteString
Call Crlf
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
mov eax,10
L1:
	sub eax,1
	sub ecx,1
	cmp ebx,0
	jz encoding
	jnz decoding
	encoding:
	;rol [edx + ecx], [edi + eax]
	push ebx
	mov bl,[edx + ecx]
	push ecx
	mov cl,[edi + eax]
	rol bl,cl
	pop ecx
	mov [edx + ecx],bl
	pop ebx
	jmp keyCheck

	decoding:
	;ror [edx + ecx], [edi + eax]
	push ebx
	mov bl,[edx + ecx]
	push ecx
	mov cl,[edi + eax]
	ror bl,cl
	pop ecx
	mov [edx + ecx],bl
	pop ebx

	keyCheck:
	cmp eax,0
	jnz looper
	mov eax,10
	looper:
add ecx,1
loop L1
pop ecx
pop eax
ret
Crypt ENDP
end main
