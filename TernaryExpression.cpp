#include "ternaryexpression.h"

TernaryExpression::TernaryExpression(Expression* first, Expression* second, Expression* third, char operation) 
    : first(first), second(second), third(third), operation(operation) {}

double TernaryExpression::evaluate() {
    switch (operation) {
        case '?': // Assuming '?' as the ternary conditional operator
            return first->evaluate() != 0 ? second->evaluate() : third->evaluate();
        // Add more cases here for other ternary operators
        default:
            throw "Unsupported operation";
    }
}
