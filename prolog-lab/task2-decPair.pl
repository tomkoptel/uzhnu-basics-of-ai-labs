% an empty list does not have even elements
decPair([], []).
decPair([X0, X1 | L], [X0, X1_INCREMENTED | R]) :-
    X1_INCREMENTED is X1 - 1,
    decPair(L, R).
