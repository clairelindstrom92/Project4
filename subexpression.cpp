#include <iostream>
#include <sstream>
using namespace std;

#include "expression.h"
#include "subexpression.h"
#include "operand.h"
#include "binaryexpression.h"      // Include BinaryExpression
#include "ternaryexpression.h"     // Include TernaryExpression
#include "quaternaryexpression.h"  // Include QuaternaryExpression
// Include other necessary headers...

SubExpression::SubExpression(Expression* left, Expression* right) {
    this->left = left;
    this->right = right;
}

Expression* SubExpression::parse(stringstream& in) {
    Expression* left;
    Expression* right;
    Expression* third;
    Expression* fourth;
    char operation, paren;

    left = Operand::parse(in);
    in >> operation;

    switch (operation) {
        case '+':
        case '-':
        case '*':
        case '/':
        case '%':
        case '^':
        case '<':
        case '>':
        case '&':
            right = Operand::parse(in);
            in >> paren;
            return new BinaryExpression(left, right, operation);

        case '?':
            right = Operand::parse(in);
            third = Operand::parse(in);
            in >> paren;
            return new TernaryExpression(left, right, third, operation);

        case '#':
            right = Operand::parse(in);
            third = Operand::parse(in);
            fourth = Operand::parse(in);
            in >> paren;
            return new QuaternaryExpression(left, right, third, fourth, operation);

        // Handle other cases or default case as necessary
    }

    // Handle any remaining or default cases
    return nullptr;
}
