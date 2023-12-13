#ifndef TERNARYEXPRESSION_H
#define TERNARYEXPRESSION_H

#include "expression.h"

class TernaryExpression : public Expression {
public:
    TernaryExpression(Expression* first, Expression* second, Expression* third, char operation);
    virtual double evaluate() override;

private:
    Expression* first;
    Expression* second;
    Expression* third;
    char operation;
};

#endif // TERNARYEXPRESSION_H
