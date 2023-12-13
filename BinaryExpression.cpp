// CMSC 330 Advanced Programming Languages
// Project 2 Skeleton
// UMGC CITE
// Spring 2023
// Claire Lindstrom
#include "binaryexpression.h"

BinaryExpression::BinaryExpression(Expression* left, Expression* right, char operation) 
    : left(left), right(right), operation(operation) {}

double BinaryExpression::evaluate() {
    switch (operation) {
        case '+':
            return left->evaluate() + right->evaluate();
        case '-':
            return left->evaluate() - right->evaluate();
        case '*':
            return left->evaluate() * right->evaluate();
        case '/':
            return left->evaluate() / right->evaluate();
        // Add more cases here for other binary operators
        default:
            throw "Unsupported operation";
    }
}
