// CMSC 330 Advanced Programming Languages
// Project 2 Skeleton
// UMGC CITE
// Spring 2023
// Claire Lindstrom

// This file contains the body of the function parseName contained in the Operand class. That function
// parses the next token. When the next non-whitespace character is a digit, it assumes a literal is next. 
// When the next character is a left parenthesis, a recursive call is made to parse the subexpression.
// Otherwise the next token is assumed to a variable. No checks are made to ensure correct syntax.

#include <cctype>
#include <iostream>
#include <sstream>
#include <list>
#include <string>
using namespace std;

#include "expression.h"
#include "subexpression.h"
#include "operand.h"
#include "variable.h"
#include "literal.h"
#include "parse.h"
#include "unaryexpression.h"       // Include header for UnaryExpression
#include "binaryexpression.h"      // Include header for BinaryExpression
#include "ternaryexpression.h"     // Include header for TernaryExpression
#include "quaternaryexpression.h"  // Include header for QuaternaryExpression

Expression* Operand::parse(stringstream& in) {
    char paren, operation;
    double value;

    in >> ws;
    if (isdigit(in.peek())) {
        in >> value;
        Expression* literal = new Literal(value);
        return literal;
    }
    if (in.peek() == '(') {
        in >> paren; // consume '('
        in >> ws;
        operation = in.peek();  // Check for operators or sub-expressions

        switch(operation) {
            case '+': // Binary Addition
            case '-': // Binary Subtraction
            case '*': // Multiplication
            case '/': // Division
            case '%': // Remainder
            case '^': // Exponentiation
            case '<': // Minimum
            case '>': // Maximum
            case '&': // Average
                in.ignore(); // Ignore the operation character
                Expression* left = parse(in);
                Expression* right = parse(in);
                in >> paren; // Consume closing ')'
                return new BinaryExpression(left, right, operation);

            case '?': // Ternary Conditional
                in.ignore(); // Ignore the operation character
                Expression* first = parse(in);
                Expression* second = parse(in);
                Expression* third = parse(in);
                in >> paren; // Consume closing ')'
                return new TernaryExpression(first, second, third, operation);

            case '#': // Quaternary Conditional
                in.ignore(); // Ignore the operation character
                Expression* firstQ = parse(in);
                Expression* secondQ = parse(in);
                Expression* thirdQ = parse(in);
                Expression* fourth = parse(in);
                in >> paren; // Consume closing ')'
                return new QuaternaryExpression(firstQ, secondQ, thirdQ, fourth, operation);
        }

        // Handle Unary Expression
        if (in.peek() == '~') {
            in >> operation; // consume '~'
            Expression* operand = parse(in);
            return new UnaryExpression(operand, operation);
        }
    }
    else
        return new Variable(parseName(in));
    return nullptr;
}
