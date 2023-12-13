// CMSC 330 Advanced Programming Languages
// Project 2 Skeleton
// UMGC CITE
// Spring 2023

#include "symboltable.h"  // Only one include is needed

using namespace std;

void SymbolTable::insert(string variable, double value) {
    // Directly construct the Symbol in the vector to avoid unnecessary copying
    elements.emplace_back(variable, value);
}

double SymbolTable::lookUp(string variable) const {
    // Use range-based for loop for better readability and efficiency
    for (const auto& element : elements) {
        if (element.variable == variable)
            return element.value;
    }
    return -1; // Return -1 if the variable is not found
}

void SymbolTable::clear() {
    elements.clear();  // Clear the vector of symbols
}
