#include "quaternaryexpression.h"

QuaternaryExpression::QuaternaryExpression(Expression* first, Expression* second, Expression* third, Expression* fourth, char operation) 
    : first(first), second(second), third(third), fourth(fourth), operation(operation) {}

double QuaternaryExpression::evaluate() {
    switch (operation) {
        case '#': // Assuming '#' as the quaternary operator
            if (first->evaluate() < 0)
                return second->evaluate();
            else if (first->evaluate() == 0)
                return third->evaluate();
            else
                return fourth->evaluate();
        // Add more cases here for other quaternary operators
        default:
            throw "Unsupported operation";
    }
}
