// CMSC 330 Advanced Programming Languages
// Project 2 Skeleton
// UMGC CITE
// Spring 2023
// Claire Lindstrom
// This file contains the definition of the Expression class, which is an abstract class that contains one
// abstract (pure virtual) function named evaluate, which must be implemented by all its subclasses.

class expression {
public: 
    virtual double evaluate() = 0;
    // Add new virtual functions here for new operations
};
