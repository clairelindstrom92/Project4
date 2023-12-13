// SymbolTable.h
#ifndef SYMBOLTABLE_H
#define SYMBOLTABLE_H

#include <string>
#include <vector>

class SymbolTable {
public:
    SymbolTable();
    void insert(std::string variable, double value);
    double lookUp(std::string variable) const;
    void clear();

private:
    struct Symbol {
        Symbol(std::string variable, double value) : variable(variable), value(value) {}
        std::string variable;
        double value;
    };
    std::vector<Symbol> elements;
};

#endif // SYMBOLTABLE_H
