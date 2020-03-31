incrementList([],[],_).
incrementList([X | List], [X2 | List2],Value) :-
    incrementList(List,List2, Value),
    X2 is X + Value.