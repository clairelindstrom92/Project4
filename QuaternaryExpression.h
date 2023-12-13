#ifndef QUATERNARYEXPRESSION_H
#define QUATERNARYEXPRESSION_H

#include "expression.h"

class QuaternaryExpression : public Expression {
public:
    QuaternaryExpression(Expression* first, Expression* second, Expression* third, Expression* fourth, char operation);
    virtual double evaluate() override;

private:
    Expression* first;
    Expression* second;
    Expression* third;
    Expression* fourth;
    char operation;
};

#endif // QUATERNARYEXPRESSION_H
