David's Bitmap Renderer!

What it does:	-BMP Files will render in false-color in the console. The color is based off of part of the value at each
			pixel in the BMP file, and what the Irvine32 library interprets those values as when it displays
			them in the console.

Requires:	-BMP file with a width that is divisible by 4 without a remainder.
		-Width must be less than the width of the console to display properly.
		-Files must be flipped upside-down.

Problems:	-Files with a width that is not divisible by 4 without a remainder will render incorrectly.
		-Attempts to fix the issue above resulted in files divisible by 4 without a remainder NOT rendering correctly.

Sample Images included (copy and paste into console):
wit.bmp
test2.bmp
test_image2.bmp
Lab6Output.bmp
aapl.bmp
leica.bmp