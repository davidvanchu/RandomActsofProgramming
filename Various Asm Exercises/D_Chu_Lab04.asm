INCLUDE Irvine32.inc
;more loop practice
.data
firstArray BYTE 0, 16, 32, 48, 64, 80, 96, 112
firstBLength = ($ - firstArray)
firstLength = firstBLength / TYPE firstArray
firstHalfLength = firstLength / 2

secondArray BYTE 0, 16, 32, 48, 64, 80, 96, 112
secondBLen = ($ - secondArray)
secondLen = secondBLen / TYPE secondArray
secondHalfLen = secondLen / 2

fiboArray BYTE 0, 0, 0, 0, 0, 0, 0
fiboArrayLen = ($ - fiboArray)


.code
main PROC

;reverse an array
mov ecx, firstHalfLength
mov esi, 0
mov edi, firstLength - 1
L1:
mov al, firstArray[esi]
xchg al, firstArray[edi]
mov firstArray[esi],al
inc esi
dec edi
loop L1

;roll an array
mov ecx, secondLen - 1
mov esi, 0
L2:
mov bl, secondArray[ecx]
xchg bl, secondArray[ecx-1]
mov secondArray[ecx],bl
loop L2


;fiboooo
mov ecx, fiboArrayLen - 2
mov ax, 1h
mov fiboArray, al
mov fiboArray + 1, al
mov ebx, 0h
mov ebp, 1h
mov edx, 2h

L3:
mov al, [fiboArray + ebx]
add al, [fiboArray + ebp]
mov [fiboArray + edx], al
inc ebx
inc ebp
inc edx
loop L3

Invoke ExitProcess,0
main ENDP
end main