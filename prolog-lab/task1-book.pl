% author(X, Y)  (X — назва книги, Y — пріз- вище автора)
% book(X, Y, Z) (X — назва, Y — ціна, Z — жанр (підручник, роман, драма, поезія і т.п.))
% playwright(X): Х— має драматичні твори;
% textbooksAuthor(X): X — автор принаймні 2 підручників;
% cheapest(X,Y): X—найдешевша книга автора Y.

book(lotr, 10, novel).
book(hobbit, 5, novel).
book(clean_code, 11, textbook).
book(clean_coder, 12, textbook).
book(dsl, 5, textbook).
book(romeo_and_juliet, 8, drama).
book(legacy_code, 10, textbook).
author(lotr, tolkien).
author(hobbit, tolkien).
author(clean_code, martin).
author(clean_coder, martin).
author(dsl, martin).
author(romeo_and_juliet, shakespeare).
author(legacy_code, feathers).

playwright(A) :- author(B, A), book(B, _, drama).
textbooksAuthor(A) :- author(B1, A), book(B1, _, textbook), author(B2, A), book(B2, _, textbook), B1 \== B2.

notTheCheapestBook(B) :- book(B, P1, _), book(B2, P2, _), B \== B2, P1 > P2.
cheapestBook(B) :- \+notTheCheapestBook(B).
cheapest(B, A) :- author(B, A), cheapestBook(B).
