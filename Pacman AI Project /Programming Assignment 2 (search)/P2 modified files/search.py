# search.py
# ---------
# Licensing Information:  You are free to use or extend these projects for
# educational purposes provided that (1) you do not distribute or publish
# solutions, (2) you retain this notice, and (3) you provide clear
# attribution to UC Berkeley, including a link to http://ai.berkeley.edu.
# 
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


"""
In search.py, you will implement generic search algorithms which are called by
Pacman agents (in searchAgents.py).
"""

import util


class SearchProblem:
    """
    This class outlines the structure of a search problem, but doesn't implement
    any of the methods (in object-oriented terminology: an abstract class).

    You do not need to change anything in this class, ever.
    """

    def getStartState(self):
        """
        Returns the start state for the search problem.
        """
        util.raiseNotDefined()

    def isGoalState(self, state):
        """
          state: Search state

        Returns True if and only if the state is a valid goal state.
        """

        util.raiseNotDefined()

    def getSuccessors(self, state):
        """
          state: Search state

        For a given state, this should return a list of triples, (successor,
        action, stepCost), where 'successor' is a successor to the current
        state, 'action' is the action required to get there, and 'stepCost' is
        the incremental cost of expanding to that successor.
        """
        util.raiseNotDefined()

    def getCostOfActions(self, actions):
        """
         actions: A list of actions to take

        This method returns the total cost of a particular sequence of actions.
        The sequence must be composed of legal moves.
        """
        util.raiseNotDefined()


def tinyMazeSearch(problem):
    """
    Returns a sequence of moves that solves tinyMaze.  For any other maze, the
    sequence of moves will be incorrect, so only use this for tinyMaze.
    """
    from game import Directions
    s = Directions.SOUTH
    w = Directions.WEST
    return  [s, s, w, s, w, w, s, w]

def nullHeuristic(state, problem=None):
    """
    A heuristic function estimates the cost from the current state to the nearest
    goal in the provided SearchProblem.  This heuristic is trivial.
    """
    return 0

def GraphSearch(fringe,problem, heuristic = nullHeuristic):
    '''
    #algo to run graph search on different data structures
    ##PARAMS:
        #problem    - search problem that needs to be solved
        #fringe     - (queue/stack data structure with push and pop) used to manage elements in problem
        #heuristic  - (trivial)-- estimates the cost from the current state to the nearest goal in the provided problem
    ##RETURN: returns a list of directions to solve the problem
    '''
    #push initial problem node into fringe (state with action & cost)
    fringe.push((problem.getStartState(), [], heuristic(problem.getStartState(), problem)))
    #empty list to track visted nodes
    visited = []
    #while fringe not empty--> still states in fringe to be searched
    while fringe.isEmpty()==0:
        #pop state, action, and cost in problem (path)
        state, actions, cost = fringe.pop()
        #if current state not visited, mark as visited (check goal/successors)
        if state not in visited:
            visited.append(state)
            #if current state is goal, return the list of actions to goal state
            if problem.isGoalState(state):
                return actions
            #otherwise for next state, actions and cost in problem (successor of curr state)
            #push to fringe
            for next in problem.getSuccessors(state):
                fringe.push((next[0], actions + [next[1]], cost + next[2])) #next[0] is new state, next[1] is new actions, next[2] is new costs
    return []

def depthFirstSearch(problem):
    """
    Search the deepest nodes in the search tree first.
    Your search algorithm needs to return a list of actions that reaches the
    goal. Make sure to implement a graph search algorithm.

    To get started, you might want to try some of these simple commands to
    understand the search problem that is being passed in:

    print "Start:", problem.getStartState()
    print "Is the start a goal?", problem.isGoalState(problem.getStartState())
    print "Start's successors:", problem.getSuccessors(problem.getStartState())
    """
    "*** YOUR CODE HERE ***"
    #print "Start:", problem.getStartState()
    #print "Is the start a goal?", problem.isGoalState(problem.getStartState())
    #print "Start's successors:", problem.getSuccessors(problem.getStartState())
    fringe = util.Stack()   #graph search w/ stack for DFS
    #return actions to solve problem (using stack)
    return GraphSearch(fringe, problem)
    #util.raiseNotDefined()


def breadthFirstSearch(problem):
    """Search the shallowest nodes in the search tree first."""
    "*** YOUR CODE HERE ***"
    fringe = util.Queue()       #graph search w/ queue for BFS
    #return actions to solve problem (using queue)
    return GraphSearch(fringe, problem)
    #util.raiseNotDefined()


def uniformCostSearch(problem):
    "*** YOUR CODE HERE ***"""
    #UCS uses graph search w/ a PriorityQueue that sorts by the cost (search lowest cost first)
    #fringe: [(state, action, cost)]--> cost of the actions: problem.getCostOfActions (last pos in fringe)
    #use anonymous function (lambda) to prioritize priority queue based on lowest cost
    pq = util.PriorityQueueWithFunction(lambda priority: priority[-1])
    return GraphSearch(pq, problem)
    #util.raiseNotDefined()


def aStarSearch(problem, heuristic=nullHeuristic):
    "*** YOUR CODE HERE ***"""
    """Search the node that has the lowest combined cost and heuristic first."""
    # A* uses graph search w/  PriorityQueue, sorting lowest cost + heuristic first (combined)
    #fringe: [(state, action, cost)], use anonymous function (lambda) to prioritize queue based on lowest cost +heuristic
    pq = util.PriorityQueueWithFunction(lambda priority: priority[-1] + heuristic(priority[0], problem))
    return GraphSearch(pq, problem)
    #util.raiseNotDefined()

# Abbreviations
bfs = breadthFirstSearch
dfs = depthFirstSearch
astar = aStarSearch
ucs = uniformCostSearch





