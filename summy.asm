.begin
.org 2048

array:		5
		2
		5
		2
		5
		6
		7
		8
		9
		4
		4
		1
		8
		8
		5
		6
		6
		4
		5
		1

ld [n], %r1 ! arraylengde
ld [a], %r2 ! arraypeker
ld [s], %r3 ! sum
ld [p], %r5 ! fortegn

loop:		subcc %r1, 1, %r1
		be done

		add %r2, 4, %r2 ! neste element
		ld %r2, %r4 ! laste inn

		sub %r0, %r5, %r5 ! endrer fortegn

		cmp %r5,0 ! subtraksjon eller addisjon
		bge else
		nop

		add %r3,%r4,%r3

		ba loop
else:		sub %r3,%r4,%r3
		ba loop
done:		st %r3, [s]
		jmpl %r15 + 4, %r0

n: 20
a: 2044
p: 1
s: 0

.end
