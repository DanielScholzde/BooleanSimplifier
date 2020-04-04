## BooleanSimplifier

#### A boolean expression simplifier with an ANTLR expression parser

This little rule based program parses and simplifies boolean expressions and tries to simplify them:

    true || false && true            ==>  true
    a ? !a : true                    ==>  !a
    (a && false) || c                ==>  c
    a || (b && c) && d               ==>  a || b && c && d
    !(3 < 4) && true                 ==>  3 >= 4
    !(a != null ? a.id <= 0 : true)  ==>  a != null && a.id > 0  // Note: Test a!=null remains at the beginning
