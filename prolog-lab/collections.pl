incrementList([],[],_).
incrementList([X | List], [X2 | List2],Value) :-
    incrementList(List,List2, Value),
    X2 is X + Value.

index([],0).
index([_|TAIL], INDEX) :- index(TAIL, PREVIOUS_INDEX), INDEX is PREVIOUS_INDEX + 1.

% Increments elements if list has even number of elements.
incrementIfListEven(List, ResultList, 0) :- incList(List, ResultList, 0).
incrementIfListEven(List, ResultList, 1) :- incList(List, ResultList, 1).
incrementIfListEven([Head | Tail], Result) :-
    index([Head | Tail], Index),
    Diff is Index mod 2, incrementIfListEven([Head | Tail], Result, Diff).

% an empty list does not have even elements
decPair([], []).
decPair([X0, X1 | L], [X0, X1_INCREMENTED | R]) :-
    X1_INCREMENTED is X1 - 1,
    decPair(L, R).