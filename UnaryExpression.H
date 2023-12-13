#ifndef UNARYEXPRESSION_H
#define UNARYEXPRESSION_H

#include "expression.h"

class UnaryExpression : public Expression {
public:
    UnaryExpression(Expression* operand, char operation);
    virtual double evaluate() override;

private:
    Expression* operand;
    char operation;
};

#endif // UNARYEXPRESSION_H
