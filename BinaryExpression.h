// CMSC 330 Advanced Programming Languages
// Project 2 Skeleton
// UMGC CITE
// Spring 2023
// Claire Lindstrom
// This file contains the definition of the Expression class, which is an abstract class that contains one
// abstract (pure virtual) function named evaluate, which must be implemented by all its subclasses.
#ifndef BINARYEXPRESSION_H
#define BINARYEXPRESSION_H

#include "expression.h"

class BinaryExpression : public Expression {
public:
    BinaryExpression(Expression* left, Expression* right, char operation);
    virtual double evaluate() override;

private:
    Expression* left;
    Expression* right;
    char operation;
};

#endif // BINARYEXPRESSION_H
