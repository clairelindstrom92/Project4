#include "unaryexpression.h"

UnaryExpression::UnaryExpression(Expression* operand, char operation) 
    : operand(operand), operation(operation) {}

double UnaryExpression::evaluate() {
    switch (operation) {
        case '~': // Assuming '~' as negation
            return -operand->evaluate();
        // Add more cases here for other unary operators
        default:
            throw "Unsupported operation";
    }
}
