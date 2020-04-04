grammar BooleanGrammar;


expression:
      atom                                                           # Atomic
    | '!' expression                                                 # Not
    | '(' expression ')'                                             # Group
    | expression ('<' | '>' | '<=' | '>=' | 'instanceof') expression # CompareRel
    | expression ('==' | '!=') expression                            # CompareEq
    | expression '&' expression                                      # BitAnd
    | expression '^' expression                                      # BitXor
    | expression '|' expression                                      # BitOr
    | expression '&&' expression                                     # And
    | expression '||' expression                                     # Or
    | <assoc=right> expression '?' expression ':' expression         # Ternary
    ;

atom:
    single_expr
    ;

single_expr:
    OTHER_TOKEN ('(' method_params* ')')?
    ;

method_params:
    mmm+ mmm* ('(' method_params? ')')?
    ;

mmm:
    OTHER_TOKEN | '!' | '?' | '=' | ':' | '&' | '|'
    ;

OTHER_TOKEN:
    [-+*/,_.<>a-zA-Z0-9]+;

WS:
    [ \r\n\t] + -> channel (HIDDEN)
    ;