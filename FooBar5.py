lock = {((0, 0), (0, 0)): 0, ((0, 0), (0, 1)): 1, ((0, 0), (1, 0)): 1, ((0, 0), (1, 1)): 0, ((0, 1), (0, 0)): 1,
        ((0, 1), (0, 1)): 0, ((0, 1), (1, 0)): 0, ((0, 1), (1, 1)): 0, ((1, 0), (0, 0)): 1, ((1, 0), (0, 1)): 0,
        ((1, 0), (1, 0)): 0, ((1, 0), (1, 1)): 0, ((1, 1), (0, 0)): 0, ((1, 1), (0, 1)): 0, ((1, 1), (1, 0)): 0,
        ((1, 1), (1, 1)): 0}

key = {0: (((0, 0), (0, 0)), ((0, 0), (1, 1)), ((0, 1), (0, 1)), ((0, 1), (1, 0)), ((0, 1), (1, 1)), ((1, 0), (0, 1)),
           ((1, 0), (1, 0)), ((1, 0), (1, 1)), ((1, 1), (0, 0)), ((1, 1), (0, 1)), ((1, 1), (1, 0)), ((1, 1), (1, 1))),
       1: (((0, 0), (0, 1)), ((0, 0), (1, 0)), ((0, 1), (0, 0)), ((1, 0), (0, 0)))}

pairCombinations = ((1, 1), (1, 0), (0, 1), (0, 0))

def generateColumn(column):
    generatedColumns = []
    currentColumn = key[column[0]]

    for currentRow in range(1, len(column)):
        currentIndex = column[currentRow]
        potentialColumns = []

        for permutation in currentColumn:
            for combination in pairCombinations:
                if lock[(permutation[currentRow], combination)] == currentIndex: potentialColumns.append(permutation + (combination,)) #adds valid column (extension) to list

        currentColumn = potentialColumns

    matchColumns = [tuple(zip(*i)) for i in currentColumn] #matches two columns together to form column pairs

    for pair in matchColumns: #converts each column to binary representation for efficiency
        leftColumn = int(''.join(str(e) for e in pair[0]), 2)
        rightColumn = int(''.join(str(e) for e in pair[1]), 2)
        generatedColumns.append((leftColumn, rightColumn))

    return generatedColumns

def generateSolution(g):
    paths = {}

    for pairs in generateColumn(g[0]):
        paths[pairs[0]] = 1 #initialize as possible path
    for columns in g:
        evolution = {}
        pairs = generateColumn(columns)
        for pair in pairs:
            left, right = pair
            if left not in paths: paths[left] = 0 #impossible path
            if right not in evolution: evolution[right] = 0 #new possibility
            evolution[right] += paths[left] #grow path
        paths = evolution

    count = 0

    for value in paths:
        count += paths[value]

    return count

def solution(g):
    return generateSolution(tuple(zip(*g)))

print(solution([[True, False, True, False, False, True, True, True], [True, False, True, False, False, False, True, False], [True, True, True, False, False, False, True, False], [True, False, True, False, False, False, True, False], [True, False, True, False, False, True, True, True]]))
print("Should be 254!")
print(solution([[True, False, True], [False, True, False], [True, False, True]]))
print("Should be 4!")
print(solution([[True, True, False, True, False, True, False, True, True, False], [True, True, False, False, False, False, True, True, True, False], [True, True, False, False, False, False, False, False, False, True], [False, True, False, False, False, False, True, True, False, False]]))
print("Should be 11567")
print(solution([[False, False, True, True, True, False, False, True, True, False, True, True, False, False, False, True, True, True, True, False, True, True, False, False, False, True, False, False, False, True, True, True, False, False, False, True, False, True, True, True, True, True, True, True, False, True, False, True, False, False], [True, True, False, False, True, True, False, False, False, False, True, False, False, True, True, False, False, False, False, False, False, True, False, True, True, False, False, False, False, True, False, False, False, False, False, True, False, False, True, False, True, True, True, True, False, True, True, False, False, True], [True, False, False, True, False, False, False, False, True, False, False, False, False, True, True, False, False, False, False, False, False, False, True, False, True, False, False, False, False, True, False, False, False, True, False, False, False, False, False, False, True, False, False, True, False, True, True, True, True, False], [False, True, False, False, False, True, True, False, False, True, False, True, True, False, True, True, True, False, False, True, False, False, False, True, True, False, False, False, False, False, True, False, False, True, False, True, False, False, False, False, False, True, True, True, True, True, True, False, True, False], [True, False, True, True, False, True, False, True, False, True, False, False, False, False, False, False, False, False, True, False, False, False, True, True, True, False, True, False, False, False, True, True, True, False, False, False, False, False, True, False, True, False, True, True, True, False, False, False, True, False], [False, False, True, True, False, True, False, False, False, False, False, False, False, False, False, False, True, False, True, True, False, False, False, False, True, False, False, True, False, False, True, True, False, False, True, True, True, True, False, True, True, True, True, True, True, False, False, False, False, True], [True, False, False, False, True, False, False, True, False, False, True, False, True, False, True, True, False, False, False, True, True, False, False, True, False, False, False, True, True, False, False, True, False, True, True, False, True, True, False, False, False, False, False, True, True, True, True, True, False, True], [False, True, False, True, False, True, True, True, True, False, True, False, True, True, False, True, True, False, False, False, False, True, False, False, True, True, True, True, False, False, True, True, False, False, False, True, False, False, False, False, False, False, True, True, True, True, False, False, True, True], [True, True, False, False, False, False, False, True, True, False, False, True, False, True, False, True, False, False, False, False, False, False, False, True, True, False, False, False, False, False, True, False, False, False, True, True, True, True, False, False, True, False, False, False, False, False, True, False, False, True]]))
print("Should be 4183023720!")
