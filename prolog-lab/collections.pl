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