insertInOrederedList(X, [], [X]).
insertInOrederedList(X, [Y|Rest], [X,Y|Rest]) :-
    X @< Y, !.
insertInOrederedList(X, [Y|Rest0], [Y|Rest]) :-
    insertInOrederedList(X, Rest0, Rest).
