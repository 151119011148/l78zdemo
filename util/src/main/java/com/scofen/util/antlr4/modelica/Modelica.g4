grammar Modelica;

stored_definition
    :( 'within' ( name )? ';' )? ( ( 'final' )? class_definition ';' )*;

class_definition :
    ( 'encapsulated' )? class_prefixes class_specifier;

class_prefixes :
    ( 'partial' )?
    ( 'class'
    | 'model'
    | ( 'operator' )? 'record'
    | 'block'
    | ( 'expandable' )? 'connector'
    | 'type'
    | 'package'
    | ( 'pure' | 'impure' )? ( 'operator' )? 'function'
    | 'operator'
    );
class_specifier :
    long_class_specifier | short_class_specifier | der_class_specifier;
long_class_specifier :
    IDENT description_string composition 'end' IDENT
    | 'extends' IDENT ( class_modification )? description_string composition
    'end' IDENT;
short_class_specifier :
    IDENT '=' base_prefix type_specifier ( array_subscripts )?
    ( class_modification )? description
    | IDENT '=' 'enumeration' '(' ( ( enum_list )? | ':' ) ')' description;
der_class_specifier :
    IDENT '=' 'der' '(' type_specifier ' ,' IDENT ( ' ,' IDENT )* ')' description;
base_prefix :
    ( 'input' | 'output' )?;
enum_list :
    enumeration_literal ( ',' enumeration_literal )*;
enumeration_literal :
    IDENT description;
composition :
    element_list
    ( 'public' element_list
    | 'protected' element_list
    | equation_section
    | algorithm_section
    )*
    ( 'external' ( language_specification )?
    ( external_function_call )? ( annotation_clause )? ';' )?
    ( annotation_clause ';' )?;
language_specification :
    STRING;
external_function_call :
    ( component_reference '=' )?
    IDENT '(' ( expression_list )? ') ';
element_list :
    ( element ';' )*;
element :
    import_clause
    | extends_clause
    | ( 'redeclare' )?
    ( 'final' )?
    ( 'inner' )? ( 'outer' )?
    ( class_definition
    | component_clause
    | 'replaceable' ( class_definition | component_clause )
    ( constraining_clause description )?
    );
import_clause :
    'import'
    ( IDENT '=' name
    | name ( '.*' | '.' ( '*' | '(' import_list ')*' ) )?
    )
    description;
import_list :
    IDENT ( ' ,' IDENT )*;

extends_clause :
    'extends' type_specifier ( class_modification )? ( annotation_clause )?;
constraining_clause :
    'constrainedby' type_specifier ( class_modification )?;

component_clause :
    type_prefix type_specifier ( array_subscripts )? component_list;
type_prefix :
    ( 'flow' | 'stream' )?
    ( 'discrete' | 'parameter' | 'constant' )?
    ( 'input' | 'output' )?;
component_list :
    component_declaration ( ' ,' component_declaration )*;
component_declaration :
    declaration ( condition_attribute )? description;
condition_attribute :
    'if' expression;
declaration :
    IDENT ( array_subscripts )? ( modification )?;
modification :
    class_modification ( '=' expression )?
    | '=' expression
    | ':=' expression;
class_modification :
    '(' ( argument_list )? ') ';
argument_list :
    argument ( ' ,' argument )*;
argument :
    element_modification_or_replaceable
    | element_redeclaration;
element_modification_or_replaceable :
    ( 'each' )? ( 'final' )? ( element_modification | element_replaceable );
element_modification :
    name ( modification )? description_string;
element_redeclaration :
    'redeclare' ( 'each' )? ( 'final' )?
    ( short_class_definition | component_clause1 | element_replaceable );
element_replaceable :
    'replaceable' ( short_class_definition | component_clause1 )
    ( constraining_clause )?;
component_clause1 :
    type_prefix type_specifier component_declaration1;
component_declaration1 :
    declaration description;
short_class_definition :
    class_prefixes short_class_specifier;

equation_section :
    ( 'initial' )? equation ( equation ';' )*;
algorithm_section :
    ( 'initial' )? 'algorithm' ( statement ';' )*;
equation :
    ( simple_expression '=' expression
    | if_equation
    | for_equation
    | connect_clause
    | when_equation
    | component_reference function_call_args
    )
    description;
statement :
    ( component_reference ( ':=' expression | function_call_args )
    | '(' output_expression_list ') ' ':='
    component_reference function_call_args
    | 'break'
    | 'return'
    | if_statement
    | for_statement
    | while_statement
    | when_statement
    )
    description;
if_equation :
    'if' expression 'then'
    ( equation ';' )*
    ( 'elseif' expression 'then'
    ( equation ';' )*
    )*
    ( 'else'
    ( equation ';' )*
    )?
    'end' 'if';
if_statement :
    'if' expression 'then'
    ( statement ';' )*
    ( 'elseif' expression 'then'
    ( statement ';' )*
    )*
    ( 'else'
    ( statement ';' )*
    )?
    'end' 'if';
for_equation :
    'for' for_indices 'loop'
    ( equation ';' )*
    'end' 'for';
for_statement :
    'for' for_indices 'loop'
    ( statement ';' )*
    'end' 'for';
for_indices :
    for_index ( ' ,' for_index )*;
for_index :
    IDENT ( 'in' expression )?;
while_statement :
    'while' expression 'loop'
    ( statement ';' )*
    'end' 'while';
when_equation :
    'when' expression 'then'
    ( equation ';' )*
    ( 'elsewhen' expression 'then'
    ( equation ';' )*
    )*
    'end' 'when';
when_statement :
    'when' expression 'then'
    ( statement ';' )*
    ( 'elsewhen' expression 'then'
    ( statement ';' )*
    )*
    'end' 'when';
connect_clause :
    'connect' '(' component_reference ' ,' component_reference ') ';

expression :
    simple_expression
    | 'if' expression 'then' expression
    ( 'elseif' expression 'then' expression )*
    'else' expression;
simple_expression :
    logical_expression ( ':' logical_expression ( ':' logical_expression )? )?;
logical_expression :
    logical_term ( 'or' logical_term )*;
logical_term :
    logical_factor ( 'and' logical_factor )*;
logical_factor :
    ( 'not' )? relation;
relation :
    arithmetic_expression ( relational_operator arithmetic_expression )?;
relational_operator :
    ' <' | ' <=' | ' >' | ' >=' | '==' | ' < >';
arithmetic_expression :
    ( add_operator )? term ( add_operator term )*;
add_operator :
    '+' | '-' | '.+' | '.-';
term :
    factor ( mul_operator factor )*;
mul_operator :
    '*' | '/' | '.*' | './';
factor :
    primary ( ('^' | '.^') primary )?;
primary :
    UNSIGNED_NUMBER
    | STRING
    | 'false'
    | 'true'
    | ( component_reference | 'der' | 'initial' | 'pure' ) function_call_args
    | component_reference
    | '(' output_expression_list ') '
    | '(' expression_list ( ';' expression_list )* ')?'
    | '(' array_arguments ')*'
    | 'end';
UNSIGNED_NUMBER :
    UNSIGNED_INTEGER | UNSIGNED_REAL;
type_specifier :
    ('.')? name;
name :
    IDENT ( '.' IDENT )*;
component_reference :
    ( '.' )? IDENT ( array_subscripts )? ( '.' IDENT ( array_subscripts )? )*;
function_call_args :
    '(' ( function_arguments )? ') ';
function_arguments :
    expression ( ' ,' function_arguments_non_first | 'for' for_indices )?
    | function_partial_application ( ' ,' function_arguments_non_first )?
    | named_arguments;
function_arguments_non_first :
    function_argument ( ' ,' function_arguments_non_first )?
    | named_arguments;
array_arguments :
    expression ( ' ,' array_arguments_non_first | 'for' for_indices )?;
array_arguments_non_first :
    expression ( ' ,' array_arguments_non_first )?;
named_arguments : named_argument ( ' ,' named_arguments )?;
named_argument : IDENT '=' function_argument;
function_argument :
    function_partial_application | expression;
function_partial_application :
    'function' type_specifier '(' ( named_arguments )? ') ';
output_expression_list :
    ( expression )? ( ' ,' ( expression )? )*;
expression_list :
    expression ( ' ,' expression )*;
array_subscripts :
    '(' subscript ( ' ,' subscript )* ')?';
subscript :
    ':' | expression;
description :
    description_string ( annotation_clause )?;
description_string :
    ( STRING ( '+' STRING )* )?;
annotation_clause :
    'annotation' class_modification;

IDENT : NONDIGIT ( DIGIT | NONDIGIT )* | Q_IDENT;
Q_IDENT : '\'' ( Q_CHAR | S_ESCAPE )* '\'';
NONDIGIT : '_' | 'a' .. 'z' | 'A' .. 'Z';
STRING : '"' ( S_CHAR | S_ESCAPE )* '"';
S_CHAR  : ~ ["\\] ;
Q_CHAR : NONDIGIT | DIGIT | '!' | '#' | '$' | '%' | '&' | '(' | ')' | '*' | '+' | ',' | '-' | '.' | '/' | ':' | ';' | '<' | '>' | '=' | '?' | '@' | '[' | ']' | '^' | '{' | '}' | '|' | '~' | ' ' | '"';
S_ESCAPE : '\’' | '\"' | '\?' | '\\'
| '\a' | '\b' | '\f' | '\n' | '\r' | '\t' | '\v';
DIGIT : '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9';
UNSIGNED_INTEGER : DIGIT ( DIGIT )*;
UNSIGNED_REAL :
    UNSIGNED_INTEGER '.' ( UNSIGNED_INTEGER )?
    | UNSIGNED_INTEGER ( '.' ( UNSIGNED_INTEGER )? )?
    ( 'e' | 'E' ) ( '+' | '-' )? UNSIGNED_INTEGER
    | '.' UNSIGNED_INTEGER ( ( 'e' | 'E' ) ( '+' | '-' )? UNSIGNED_INTEGER )?;
