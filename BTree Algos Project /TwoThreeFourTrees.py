'''
Algorithms Programming Project 1: 2,3,4-trees
Part 1,2, and 3: print tree, search tree, insert new key and split node
Noah Hendlish

'''
import random
class TreeNode:
    def __init__(self, key1=None, key2=None, key3=None):  # set up so keys can be unsorted (in an order)
        # when intialized, keys will be sorted and left bounded
        self.childr = None  # child1, most-right:    child1's keys >= key3
        self.childmr = None  # child2, mid-right:     key2 < child2's keys <= key3
        self.childml = None  # child3, mid-left:      key1< child3's keys <= key2
        self.childl = None  # child4, most-left:     child4 < key1
        self.children = [self.childr, self.childmr, self.childml, self.childl]

        if (key2 == None and key3 == None):  # if there is only 1 key:
            self.keym = None  # the middle key is None
            self.keyl = key1  # left key is key1
            self.keyr = None  # right key is None
            self.keys = [key1, key2, key3]
        elif (key2 != None):  # if a second key exists, we have two keys:
            if (key2 >= key1):  # if second key is greater than or equal to first key
                self.keym = key2  # middle key is second key
                self.keyl = key1  # left key is first key
                self.keyr = None  # right key is None
                self.keys = [key1, key2, key3]
            else:  # otherwise (if key2 < key1)
                self.keym = key1  # middle key is key 1
                self.keyl = key2  # left key is key 2
                self.keyr = None  # right key is None
                self.keys = [key2, key1, key3]
        elif (key3 != None):  # if a third key exists, we have 3 keys
            keys = [key1, key2, key3]  # create array of keys
            self.keyr = max(keys)  # max key value in array is the right key
            self.keyl = min(keys)  # min key value in array is the left key
            if (key1 != max(keys) and key1 != min(keys)):  # if statements to find the middle key
                self.keym = key1  # (if key_i is not min or max, it is the middle key)
            if (key2 != max(keys) and key2 != min(keys)):
                self.keym = key2
            if (key3 != max(keys) and key3 != min(keys)):
                self.keym = key3

# simple print function
def print_tree(T, indent=0):
    if T != None:
        #print("\n")
        print_tree(T.childr, indent + 16)  # print rightmost children recursively
        print_tree(T.childmr, indent + 16)  # print middle-right children recursively
        # print with "none" as value for empty keys::
        print("", indent * " ", T.keyr, "\n", indent * " ", T.keym, "\n", indent * " ", T.keyl,
              "\n")  # print indentation of child, then key, new line (3 times)
        print_tree(T.childml, indent + 16)  # print middle-left children recursively
        print_tree(T.childl, indent + 16)  # print leftmost children recursively


# print nodes at each level
def print_tree_levels(T, i=0, string = "root           "):
    if (T != None):

        print(string, "\t\tat level", i, ":\t", T.keyl, T.keym, T.keyr, "\n")
        if (T.childr != None):
            #print("right child")
            print_tree_levels(T.childr, i + 1, "right child    ")
        if (T.childmr != None):
            #print("mid right child")
            print_tree_levels(T.childr, i + 1, "mid right child")
        if (T.childml != None):
            #print("mid left child")
            print_tree_levels(T.childl, i + 1, "mid left child ")
        if (T.childl != None):
            #print("left child")
            print_tree_levels(T.childl, i + 1, "left child     ")

#returns size of node
def node_size(T):  # returns node size (# of keys in a node)
    if (
        T.keyr != None):  # if the right-most key is not Null, then the node is full my tree fills its keys from left to right
        return 3  # max # of keys is 3, and node is full (left, middle and right), thus it returns 3
    if (
            T.keyr == None and T.keym != None):  # if the right key is null, but the middle key is not-Null, then we have left key and middle key
        return 2  # returns 2 (left and middle key)
    if (
                T.keyr == None and T.keym == None and T.keyl != None):  # if left key is Not-Null, but right and middle keys are NULL, there is only 1 key in node
        return 1  # returns 1 (left key is only one non-Null in the node)

#returns true if node is full
def node_full(T):  # checks if node is full, by checking if all keys have non-Null values
    if (
                T.keyl != None and T.keym != None and T.keyr != None):  # if all values are not Null, then node is full -->return true, otherwise return false
        return True
    else:
        return False

#checks if a key is in a node
def key_in_node(node, key):  # given a node, checks if Tree has key is in the node
    if (
                key == node.keyl or key == node.keym or key == node.keyr):  # if search key is in any of the keys of the given node return true, otherwise return false
        return True
    else:
        return False

#checks if node is a leaf
def is_leaf(node):  # checks if a node is a leaf
    if (
                    node.childl == None and node.childml == None and node.childmr == None and node.childr == None):  # if all of its childern are null--> return true
        return True
    else:  # otherwise, it has a child, thus current node isnt a leaf --> return false
        return False

#search tree for a key, and accumulate path taken to be printed
def search_tree(T, key,
                order_to_node=""):  # function to recursively search for a key in a tree, T        *(before any recurse, T is root node of the tree).
    if (key_in_node(T, key)):  # if the key is in the node:
        print("Key", key,
              "is in tree")  # uses key_in_node function to check if any key in current node contains the search key)
        print("Path from root to node containing", key, ": Root",
              order_to_node)  # returns the path from the root to the node
        return key
    elif (
    is_leaf(T)):                # if key is not in node (from above if statement) and it is a leaf, then the key is not in the tree
        print("Key", key, "is not in tree")  # by recursive search

    if (node_size(T) == 1):     # if node only has left key (1 key)
        if (key < T.keyl):      # if the search key is less than the left key (only key), recursively search left
            if (T.childl != None):  # if left child isnt empty node
                order_to_node += "--> Left "  # adds order from root to node
                left = search_tree(T.childl, key, order_to_node)  # recursively search left children of node
                if (key == left):   # if search key == final key in recursive search, return key
                    return left
        elif (key > T.keyl):        # if the search key is greater than the left key (only key), recursively search right
            if (T.childr != None):  # checks node is non-empty/not NULL
                order_to_node += "--> Right "  # adds order
                right = search_tree(T.childr, key, order_to_node)  # recursive call to search right child
                if (key == right):      # if search key == final key in recursive search, return key
                    return right
    elif (node_size(T) == 2):           # node only has left key and middle key --> 2 keys (no right key)
        if (key < T.keyl):              # if the search key is less than the left key (only key), recursively search left
            if (T.childl != None):      # same left search as above case (when only left key)
                order_to_node += "--> Left "  # adds order
                left = search_tree(T.childl, key, order_to_node)
                if (key == left):       # if search key == final key in recursive search, return key
                    return left
        elif (key < T.keym):            # if search key is less than middle key (same as saying greater than left key)
            if (T.childml != None):     # check Node is not empty
                order_to_node += "--> MidLeft "     # adds order
                midleft = search_tree(T.childml, key,order_to_node)  # recursive search call to search middle left child
                if (key == midleft):  # if search key == final key in recursive search, return key
                    return midleft
        elif (
            key > T.keym):          # if search key is greater than middle key, then key must be in right node (since there are only 2 keys in this node--> no Midright child)
            if (T.childr != None):  # check non empty
                order_to_node += "--> Right "  # add order
                right = search_tree(T.childr, key, order_to_node)  # recrusive search call on right child
                if (key == right):  # if search key == final key in recursive search, return key
                    return right
    elif (node_full(T)):                # node is full of keys (node has left, middle and right keys)
        if (key < T.keyl):
            if (T.childl != None):  #same as other cases like this:
                order_to_node += "--> Left "  # adds order from root to node
                left = search_tree(T.childl, key, order_to_node)  # recursive search call on left child
                if (key == left):  # if search key == final key in recursive search, return key
                    return left
        elif (key < T.keym):  #same as case when nodesize ==2
            if (T.childml != None):
                order_to_node += "--> Midleft "  # adds order
                midleft = search_tree(T.childml, key, order_to_node)
                if (key == midleft):  # if search key == final key in recursive search, return key
                    return midleft
        elif (key > T.keym):  #same as case when nodesize ==2
            if (T.childmr != None):
                order_to_node += "--> Midright "  # adds order
                midright = search_tree(T.childmr, key, order_to_node)
                if (key == midright):  # if search key == final key in recursive search, return key
                    return midright
        elif (key > T.keyr):  # if search key is greater than right-most key, search the right child recursively
            if (T.childr != None):
                order_to_node += "--> Right "  # adds order
                right = search_tree(T.childr, key, order_to_node)  # recursive call to search right child of node
                if (key == right):  # if search key == final key in recursive search, return key
                    return right

                    # if(T.childl==None and T.childr==None and T.childml==None and T.childmr==None)

#wrapper method for insert
def insert(T, key):
    if (node_full(T)):
        T = split(None, T, key)
        insert_non_full_key(T, key)
    else:
        insert_non_full_key(T, key)
    return T

#insertion on non-full node (if it has less than 3 keys)
def insert_non_full_key(T, key):
    if (is_leaf(T)): #if the node called is a non-full leaf (since in non-full key), insert key
        #Ensures keys inserted into node are in correct order (sorted correctly within the node)
        if (key > T.keyl):
            if (T.keym == None):  # key > T.keyl
                T.keym = key
            elif (T.keyr == None):
                if(T.keym > key):
                    T.keyr = T.keym
                    T.keym = key
                else:
                    T.keyr = key
        else:                   # otherwise if statement-->(key < T.keyl):
            if (T.keym == None):
                if (key <= T.keyl):  # implied
                    T.keym = T.keyl
                    T.keyl = key
                else:
                    T.keym = key
            elif (T.keyr == None):
                if (key <= T.keym):  # implied
                    new_right = T.keym
                    T.keyr = new_right
                    T.keym = T.keyl
                    T.keyl = key
                else:
                    T.keyr = key
    else:
        if (node_size(T) == 1):  # if node only has one key (1 key)
            if (key < T.keyl):  # if key to insert's value is less than nodes left key
                if (T.childl != None):  # if left child isnt empty node
                    if (node_full(T.childl)):
                        split(T, T.childl, key)
                        if (key < T.keyl):  # continues to insert key in correct spot
                            child = insert_non_full_key(T.childl, key)
                        elif (key < T.keym):
                            child = insert_non_full_key(T.childml, key)
                            # elif(key > T.keym): IS NULL
                            # child = insert_non_full_key(T.childmr, key)
                        elif (key >= T.keyr):
                            child = insert_non_full_key(T.childr, key)
                    else:
                        insert_non_full_key(T.childl, key)  # recursively search left children of node

            elif (key >= T.keyl):  # if key to insert's value is greater than nodes left key
                if (T.childr != None):
                    if (node_full(T.childr)):
                        split(T, T.childr, key)
                        if (key < T.keyl):  # continues to insert key in correct spot
                            child = insert_non_full_key(T.childl, key)
                        elif (key < T.keym):
                            if (T.childml != None):
                                child = insert_non_full_key(T.childml, key)
                        elif (key >= T.keym):
                            if (T.childmr != None and key > T.childmr.key):
                                child = insert_non_full_key(T.childmr, key)
                            else:  # elif (key >= T.keyr):
                                child = insert_non_full_key(T.childr, key)
                    else:
                        insert_non_full_key(T.childr, key)

        elif (node_size(T) == 2):  # if node has 2 keys (left and middle)
            if (key < T.keyl):
                if (T.childl != None):
                    if (node_full(T.childl)):
                        split(T, T.childl, key)
                        if (key < T.keyl):  # continues to insert key in correct spot
                            child = insert_non_full_key(T.childl, key)
                        elif (key < T.keym):
                            if (T.childml != None):
                                child = insert_non_full_key(T.childml, key)
                        elif(key < T.keyr):           # elif(key >= T.keyr):
                            if (T.childmr != None):
                                child = insert_non_full_key(T.childmr, key)
                        else:  # elif (key >= T.keyr):
                            child = insert_non_full_key(T.childr, key)


            elif (key < T.keym):
                if (T.childml != None):
                    if (node_full(T.childml)):
                        split(T, T.childml, key)  # splits child, and makes T full
                        if (key < T.keyl):  # continues to insert key in correct spot
                                child = insert_non_full_key(T.childl, key)
                        elif (key < T.keym):
                            if (T.childml != None):
                                child = insert_non_full_key(T.childml, key)
                        elif (key < T.keyr): #same as else
                            if (T.childmr != None):
                                child = insert_non_full_key(T.childmr, key)
                        else:  # elif (key >= T.keyr):
                            child = insert_non_full_key(T.childr, key)
                            #child = insert_non_full_key(T.childmr, key)
                    else:
                        child = insert_non_full_key(T.childml, key)

            elif (key >= T.keym):
                if (T.childr != None):
                    if (node_full(T.childr)):
                        split(T, T.childr, key)
                        if (key < T.keyl):  # continues to insert key in correct spot
                            child = insert_non_full_key(T.childl, key)
                        elif (key < T.keym):
                            if (T.childml != None):
                                child = insert_non_full_key(T.childml, key)
                        elif (key < T.keyr):   #already in midright key
                            if (T.childmr != None):
                                child = insert_non_full_key(T.childmr, key)
                        else:
                            child = insert_non_full_key(T.childr, key)
                    else:
                        child = insert_non_full_key(T.childr, key)

#split function to split nodes (root is first case, otherwise it splits a node)
def split(T, child, key):  # if full
    if (T == None): #if "split(parent, child_to_split, key)": child is root then T is none
        new_root = TreeNode(child .keym)
        new_root.childr = TreeNode(child.keyr)
        new_root.childl = TreeNode(child.keyl)
        if(child.childr ):
            new_root.childr.childr = child.childr
        if(child.childl ):
            new_root.childl.childl = child.childl
        if(child.childml ):
            new_root.childl.childr = child.childml
        if(child.childmr ):
            new_root.childr.childl = child.childmr
        return new_root
    else:
        # ALWAYS FLUSH LEFT
        if (T.keym == None):  # if only 1 key in parent node
            if (child == T.childr or child.keyl > T.keyl):  # splitting child that is to the right of its parent:
                T.keym = child.keym
                if (child.keyl < T.keyl): #set left key
                    if (T.childl == None):
                        T.childl = TreeNode(child.keyl)
                        # carry over childer
                        if (child.childl != None):
                            T.childl.childl = child.childl
                        if (child.childml != None):
                            T.childl.childr = child.childml

                else:                       # if (T.childml == None):, then mid left child (unlikely)
                    T.childml = TreeNode(child.keyl)
                    # carry over childer
                    if (child.childl != None):
                        T.childml.childl = child.childl
                    if (child.childml != None):
                        T.childml.childr = child.childml

                if (child.keyr > T.keyl):  # will always hold true that right childs right key is larger than its parents left key, trivially
                    T.childr = TreeNode(child.keyr)
                    # carry over childer
                    if (child.childmr != None):
                        T.childr.childl = child.childmr
                    if (child.childr != None):
                        T.childr.childr = child.childr
            elif (child == T.childl or child.keyl < T.keyl):  # elif (child == T.childl) -splitting child that is to the left of its parent:child.keyr < T.keyl
                T.keym = T.keyl
                T.keyl = child.keym
                if (child.keyr < T.keym):       #split into left and midleft positions
                    T.childl = TreeNode(child.keyl)
                    # carry over childer
                    if (child.childml != None):
                        T.childl.childl = child.childl
                    if (child.childmr != None):
                        T.childl.childr = child.childml
                    T.childml = TreeNode(child.keyr)
                    # carry over childer
                    if (child.childmr != None):
                        T.childml.childl = child.childmr
                    if (child.childr != None):
                        T.childml.childr = child.childr

                else:                       #split into midleft and midright positions
                    T.childml = TreeNode(child.keyl)
                    if (child.childl != None):
                        T.childml.childl = child.childl
                    if (child.childml != None):
                        T.childml.childr = child.childml

                    T.childmr = TreeNode(child.keyr)
                    if (child.childmr != None):
                        T.childml.childl = child.childmr
                    if (child.childr != None):
                        T.childml.childr = child.childr



        elif (T.keyr == None):  # if 2 key in parent node (not just 1)
            if (child == T.childl or child == T.childml or child.keym < T.keym): #splitting child that is to the left of its parent
                if (T.childl == child or child.keyr < T.keyl):  # CHILD IS LEFTMOST (NOT MID) TO PARENT
                    T.keyr = T.keym
                    T.keym = T.keyl
                    T.keyl = child.keym
                    T.childl = TreeNode(child.keyl)
                    # carry over children
                    if (child.childl != None):
                        T.childl.childl = child.childl
                    if (child.childml != None):
                      T.childl.childr = child.childml
                    if(T.childml == None):
                        T.childml = TreeNode(child.keyl)
                    else:
                        T.childmr = T.childml
                        T.childml = TreeNode(child.keyl)
                    # carry over children
                    if (child.childl != None):
                        T.childml.childl = child.childl
                    if (child.childml != None):
                        T.childml.childr = child.childml

                elif (T.childml == child or child.keym < T.keym):  # CHILD IS MIDLEFT OF PARENT NODE (LEFT SIDE OF MIDDLE KEY-- between left and mid key)
                    T.keyr = T.keym
                    T.keym = child.keym
                    T.childml = TreeNode(child.keyl)
                    T.childmr = TreeNode(child.keyr)

                    if (child.childmr != None):
                        T.childmr.childl = child.childmr
                    if (child.childr != None):
                        T.childmr.childr = child.childr

            # splitting children to the right of parents (T is the parent)
            elif (T.childr == child or T.keym < child.keyl or child.keyr >= T.keym):  # RIGHT/MIDRIGHT CHILD
                if(T.childr == child or T.keyr <= child.keyl): #splitting child that is to the right of its parent RIGHT
                    T.keyr = child.keym
                    # assign right key to node
                    T.childr = TreeNode(child.keyr)  # trivially right node turns into right key
                    # carry over/assign new node the children
                    if (child.childmr != None):
                        T.childr.childl = child.childmr
                    if (child.childr != None):
                        T.childr.childr = child.childr
                        # assign mid-right key to node          MIDRIGHT
                    if (T.childmr == None):
                        T.childmr = TreeNode(child.keyl)
                        #descendants:
                        if (child.childml != None): #is instance
                            T.childmr.childl = child.childl
                        if (child.childl != None):
                            T.childmr.childr = child.childml

                else:   #MIDRIGHT
                    if(T.childmr == child or T.childmr == None or child.keyl > T.keym): #is midright child (unlikely)
                        T.childmr = TreeNode(child.keyr)
                        if (child.childl != None):
                            T.childmr.childl = child.childmr                # carry over children
                        if (child.childml != None):
                            T.childmr.childr = child.childr

                    if (T.childml == None):
                        T.childml = TreeNode(child.keyl)
                        if (child.childl != None):
                            T.childml.childl = child.childl
                        if (child.childml != None):
                            T.childml.childr = child.childml

#sample tree for testing
def sample():
    new_tree = TreeNode(1)
    new_tree = insert(new_tree, 22)
    new_tree = insert(new_tree, 4)
    new_tree = insert(new_tree, 9)
    new_tree = insert(new_tree, 2)
    new_tree = insert(new_tree, 6)
    new_tree = insert(new_tree, 10)
    new_tree = insert(new_tree, 12)
    new_tree = insert(new_tree, 14)
    new_tree = insert(new_tree, 18)
    new_tree = insert(new_tree, 37)
    new_tree = insert(new_tree, 13)
    new_tree = insert(new_tree, 22)
    new_tree = insert(new_tree, 25)
    new_tree = insert(new_tree, 26)
    new_tree = insert(new_tree, 42)
    new_tree = insert(new_tree, 20)
    new_tree = insert(new_tree, 60)
    return new_tree

#create random tree
def rand_tree():
    new_tree = TreeNode(random.randint(0,200))
    new_tree = insert(new_tree,(random.randint(1,300)))
    new_tree = insert(new_tree,(random.randint(10,199)))
    new_tree = insert(new_tree,(random.randint(120,199)))
    new_tree = insert(new_tree,(random.randint(11,190)))
    new_tree = insert(new_tree,(random.randint(32,199)))
    new_tree = insert(new_tree,(random.randint(1,199)))
    new_tree = insert(new_tree,(random.randint(1,199)))
    new_tree = insert(new_tree,(random.randint(45,199)))
    new_tree = insert(new_tree,(random.randint(19,190)))
    new_tree = insert(new_tree,(random.randint(1,199)))
    new_tree = insert(new_tree,(random.randint(1,100)))
    new_tree = insert(new_tree,(random.randint(1,100)))
    new_tree = insert(new_tree,(random.randint(1,199)))
    new_tree = insert(new_tree,(random.randint(1,20)))
    new_tree = insert(new_tree,(random.randint(1,199)))
    new_tree = insert(new_tree,(random.randint(1,199)))
    new_tree = insert(new_tree,(random.randint(1,19)))
    new_tree = insert(new_tree,(random.randint(11,199)))
    new_tree = insert(new_tree,(random.randint(1,199)))
    new_tree = insert(new_tree,(random.randint(1,199)))
    new_tree = insert(new_tree,(random.randint(91,109)))
    new_tree = insert(new_tree,(random.randint(1,29)))
    new_tree = insert(new_tree,(random.randint(1,50)))
    new_tree = insert(new_tree,(random.randint(1,20)))
    return new_tree

#UI: to interact with user through console
def user():
    run = True
    tree = None
    while(run == True):
        choice = input("\n\n2-3-4 Trees\n\nEnter 1 to print tree\nEnter 2 to print levels of each node in tree \nEnter 3 to insert into the tree\n"
                       "Enter 4 to search tree\nEnter 5 to create randomized sample tree to preform actions on (i.e print/search/input)"
                       "\nEnter 6 to create test/sample tree (already built) to preform actions on (i.e print/search/input)"
                       "\nEnter 7 to clear tree (create empty tree)"
                       "\nEnter 8 to quit\n\nInput: ")
        if(choice =="1"):
            if (tree == None):
                print("\nEmpty tree. Insert values into the tree.\n")
            print("\n\n")
            print_tree(tree)
        if(choice =="2"):
            if (tree == None):
                print("\nEmpty tree. Insert values into the tree.\n")
            print_tree_levels(tree)
        if(choice =="3"):
            if(tree==None):
                value = int(input("\nTree is currently empty\nEnter value (number) to insert into new tree: "))
                print("\n\n")
                tree = TreeNode(value)
            else:
                value = int(input("\nEnter value (number) to insert into tree: "))
                tree=insert(tree, value)
        if(choice =="4"):
            if(tree==None):
                print("\nEmpty tree. Insert values into the tree.\n")
            else:
                search = int(input("\nEnter value of key (number) to search tree: "))
                print("\n\n")
                search_tree(tree, search)
        if(choice =="5"):
            tree = rand_tree()
        if (choice == "6"):
            tree = sample()
        if (choice == "7"):
            tree = None
        if(choice == "8"):
            run = False

#run UI
user()

#original test tree
def test():
    # build sample tree to test functions on (hardcoded)
    test = TreeNode(20)

    test.childr = TreeNode(22)
    test.childl = TreeNode(4, 9)
    test.childl.childl = TreeNode(2)
    test.childl.childml = TreeNode(6)
    test.childl.childr = TreeNode(10, 12, 14)
    test.childr.childl = TreeNode(18, 20)
    test.childr.childr = TreeNode(24)
    return test










#OTHER (ORIGINAL/UNFINALIZED/DRAFT)METHODS USED TO DEBUG AND COMMENT/PRINT
#
#
#------------------------------------------------------------------
def print_tree_design(T, indent=0, i =0):
    if T != None:
        print("\n")
        print_tree(T.childr, indent + 16)  # print rightmost children recursively
        print_tree(T.childmr, indent + 16)  # print middle-right children recursively
        # print with "none" as value for empty keys::
        print("/" *((indent+14)*i))
        print("", (2*indent+18) * " ", "/------/", "\n", indent * " ", T.keyr, "\n", indent * " ", T.keym, "\n",
                  indent * " ", T.keyl, "\n", (2*indent+18) * " ",
                  "\\------\\")  # print indentation of child, then key, new line (3 times)
        print("/" *((indent+14)*i))
        print_tree(T.childml, indent + 16)  # print middle-left children recursively
        print_tree(T.childl, indent + 16)  # print leftmost children recursively

def original_commented_insert_non_full_key(T, key):
    print("insert\n")
    if (is_leaf(T)): #if the node called is a non-full leaf (since in non-full key), insert key
        print("node is leaf")
        #Ensures keys inserted into node are in correct order (sorted correctly within the node)
        if (key > T.keyl):
            print("key to insert is larger than left key")
            if (T.keym == None):  # key > T.keyl
                T.keym = key
            elif (T.keyr == None):
                if(T.keym > key):
                    T.keyr = T.keym
                    T.keym = key
                else:
                    T.keyr = key
        else:                   # otherwise if statement-->(key < T.keyl):
            print("key to insert is smaller than left key")
            if (T.keym == None):
                if (key <= T.keyl):  # implied
                    T.keym = T.keyl
                    T.keyl = key
                else:
                    T.keym = key
            elif (T.keyr == None):
                if (key <= T.keym):  # implied
                    new_right = T.keym
                    T.keyr = new_right
                    T.keym = T.keyl
                    T.keyl = key
                else:
                    T.keyr = key

    else:
        print("else")
        if (node_size(T) == 1):  # if node only has one key (1 key)
            print("node only has one key")
            if (key < T.keyl):  # if key to insert's value is less than nodes left key
                if (T.childl != None):  # if left child isnt empty node
                    print("1 key-left node")
                    if (node_full(T.childl)):
                        print("1-left child full")
                        split(T, T.childl, key)
                        if (key < T.keyl):  # continues to insert key in correct spot
                            child = insert_non_full_key(T.childl, key)
                        elif (key < T.keym):
                            child = insert_non_full_key(T.childml, key)
                            # elif(key > T.keym): IS NULL
                            # child = insert_non_full_key(T.childmr, key)
                        elif (key >= T.keyr):
                            child = insert_non_full_key(T.childr, key)
                    else:
                        insert_non_full_key(T.childl, key)  # recursively search left children of node

            elif (key >= T.keyl):  # if key to insert's value is greater than nodes left key
                if (T.childr != None):
                    print("1 key-right node")
                    if (node_full(T.childr)):
                        print("1 key -right child full")
                        split(T, T.childr, key)
                        print("split done")
                        if (key < T.keyl):  # continues to insert key in correct spot
                            child = insert_non_full_key(T.childl, key)
                        elif (key < T.keym):
                            if (T.childml != None):
                                child = insert_non_full_key(T.childml, key)
                        elif (key >= T.keym):
                            if (T.childmr != None and key > T.childmr.key):
                                child = insert_non_full_key(T.childmr, key)
                            else:  # elif (key >= T.keyr):
                                child = insert_non_full_key(T.childr, key)
                    else:
                        insert_non_full_key(T.childr, key)

        elif (node_size(T) == 2):  # if node has 2 keys (left and middle)
            print("node has two keys")
            parentofT = T
            if (key < T.keyl):
                if (T.childl != None):
                    print("2-left")
                    if (node_full(T.childl)):
                        print("2-left child full")
                        split(T, T.childl, key)
                        if (key < T.keyl):  # continues to insert key in correct spot
                            child = insert_non_full_key(T.childl, key)
                        elif (key < T.keym):
                            if (T.childml != None):
                                child = insert_non_full_key(T.childml, key)
                        elif(key < T.keyr):           # elif(key >= T.keyr):
                            if (T.childmr != None):
                                child = insert_non_full_key(T.childmr, key)
                        else:  # elif (key >= T.keyr):
                            child = insert_non_full_key(T.childr, key)
                    else:
                        print("inserting left --- was an error ln 271)")
                        insert_non_full_key(T.childl, key)

            elif (key < T.keym):
                if (T.childml != None):
                    print("2-leftmid")
                    if (node_full(T.childml)):
                        print("2-leftmid child full")
                        split(T, T.childml, key)  # splits child, and makes T full
                        if (key < T.keyl):  # continues to insert key in correct spot
                                child = insert_non_full_key(T.childl, key)
                        elif (key < T.keym):
                            if (T.childml != None):
                                child = insert_non_full_key(T.childml, key)
                        elif (key < T.keyr): #same as else
                            if (T.childmr != None):
                                child = insert_non_full_key(T.childmr, key)
                        else:  # elif (key >= T.keyr):
                            child = insert_non_full_key(T.childr, key)
                            #child = insert_non_full_key(T.childmr, key)
                    else:
                        child = insert_non_full_key(T.childml, key)

            elif (key >= T.keym):
                if (T.childr != None):
                    print("2-right")
                    if (node_full(T.childr)):
                        print("2-right child full")
                        split(T, T.childr, key)
                        if (key < T.keyl):  # continues to insert key in correct spot
                            child = insert_non_full_key(T.childl, key)
                        elif (key < T.keym):
                            if (T.childml != None):
                                child = insert_non_full_key(T.childml, key)
                        elif (key < T.keyr):   #already in midright key
                            if (T.childmr != None):
                                child = insert_non_full_key(T.childmr, key)
                        else:
                            child = insert_non_full_key(T.childr, key)
                            ##CHECK VALIDITY
                            #if (T.childmr != None):

                            #else:  # elif (key >= T.keyr): --> Right is not none in this tree...

                            #elif (key < T.keyr):
                            #child = insert_non_full_key(T.childmr, key)
                    else:
                        child = insert_non_full_key(T.childr, key)
        else:
            print("error")

def original_commented_split(T, child, key):  # if full
    print("left child of node split is ", child.keyl)
    if (T == None): #WHAT IS BASE CASE FOR ROOT? if child is root then T is none, right?
        print("new root")
        new_root = TreeNode(child .keym)
        new_root.childr = TreeNode(child.keyr)
        new_root.childl = TreeNode(child.keyl)
        if(child.childr ):
            new_root.childr.childr = child.childr
        if(child.childl ):
            new_root.childl.childl = child.childl
        if(child.childml ):
            new_root.childl.childr = child.childml
        if(child.childmr ):
            new_root.childr.childl = child.childmr
        return new_root
    else:
        # ALWAYS FLUSH LEFT
        print("split-inner or leaf--not root")
        if (T.keym == None):  # if only 1 key in parent node
            print("only one key in parent node")
            if (child == T.childr or child.keyl > T.keyl):  # splitting child that is to the right of its parent:
                T.keym = child.keym
                print("splitting child that is to the right of T (its parent)")
                if (child.keyl < T.keyl):
                    if (T.childl == None):
                        T.childl = TreeNode(child.keyl)
                        # carry over childer
                        if (child.childl != None):
                            T.childl.childl = child.childl
                        if (child.childml != None):
                            T.childl.childr = child.childml

                else:  # if (T.childml == None):
                    T.childml = TreeNode(child.keyl)
                    # carry over childer
                    if (child.childl != None):
                        T.childml.childl = child.childl
                    if (child.childml != None):
                        T.childml.childr = child.childml

                if (child.keyr > T.keyl):  # will always hold true that right childs right key is larger than its parents left key
                    T.childr = TreeNode(child.keyr)
                    # trivially we get HERE
                    # carry over childer
                    if (child.childmr != None):
                        T.childr.childl = child.childmr
                    if (child.childr != None):
                        T.childr.childr = child.childr
            elif (child == T.childl or child.keyl < T.keyl):  # elif (child == T.childl) #splitting child that is to the left of its parent:child.keyr < T.keyl
                print("splitting child that is to the left of T (its parent)")
                T.keym = T.keyl
                T.keyl = child.keym
                if (child.keyr < T.keym):
                    T.childl = TreeNode(child.keyl)
                    # carry over childer
                    if (child.childml != None):
                        T.childl.childl = child.childl
                    if (child.childmr != None):
                        T.childl.childr = child.childml
                    T.childml = TreeNode(child.keyr)
                    # carry over childer
                    if (child.childmr != None):
                        T.childml.childl = child.childmr
                    if (child.childr != None):
                        T.childml.childr = child.childr


                else:
                    T.childml = TreeNode(child.keyl)
                    if (child.childl != None):
                        T.childml.childl = child.childl
                    if (child.childml != None):
                        T.childml.childr = child.childml

                    T.childmr = TreeNode(child.keyr)
                    if (child.childmr != None):
                        T.childml.childl = child.childmr
                    if (child.childr != None):
                        T.childml.childr = child.childr



        elif (T.keyr == None):  # if 2 key in parent node (not just 1)
            print("two keys in parent node")
            if (child == T.childl or child == T.childml or child.keym < T.keym): #splitting child that is to the left of its parent

                if (T.childl == child or child.keyr < T.keyl):  # CHILD IS LEFTMOST (NOT MID) TO PARENT
                    T.keyr = T.keym
                    T.keym = T.keyl
                    T.keyl = child.keym

                    T.childl = TreeNode(child.keyl)
                    # carry over children
                    if (child.childl != None):
                        T.childl.childl = child.childl
                    if (child.childml != None):
                      T.childl.childr = child.childml


                    if(T.childml == None):
                        T.childml = TreeNode(child.keyl)
                    else:
                        T.childmr = T.childml
                        T.childml = TreeNode(child.keyl)
                    # carry over children
                    if (child.childl != None):
                        T.childml.childl = child.childl
                    if (child.childml != None):
                        T.childml.childr = child.childml

                ###NEEED TO FIX thiS
                elif (T.childml == child or child.keym < T.keym):  # midleft
                    T.keyr = T.keym
                    T.keym = child.keym
                    T.childml = TreeNode(child.keyl)
                    T.childmr = TreeNode(child.keyr)

                    if (child.childmr != None):
                        T.childmr.childl = child.childmr
                    if (child.childr != None):
                        T.childmr.childr = child.childr
                    # assign left key to node
                    if (T.childmr == None):
                        # descendants:
                        if (child.childml != None):  # is instance
                            T.childmr.childl = child.childl

                        if (child.childl != None):
                            T.childmr.childr = child.childml
                    else:
                        print("error - mid right child full")


                elif (T.childmr == child or T.childmr == None or child.keyl > T.keym): #midright?
                    T.childmr = TreeNode(child.keyr)

                    if (T.childmr == None):
                        T.childmr = TreeNode(child.keyr)

                    if (T.childml == None):
                        T.childml = TreeNode(child.keyl)

                elif (T.childml != None):
                    print("figure it out")

                    ########
            # splitting children to the right of parents (T is the parent)
            elif (T.childr == child or T.keym < child.keyl or child.keyr >= T.keym):  # RIGHT CHILD
                print("splitting child that is to the right of its parent")
                T.keyr = child.keym
                # assign right key to node
                T.childr = TreeNode(child.keyr)  # trivially right node turns into right key
                # carry over/assign new node the children
                if (child.childmr != None):
                    T.childr.childl = child.childmr
                if (child.childr != None):
                    T.childr.childr = child.childr

                # assign left key to node
                if (T.childmr == None):
                    T.childmr = TreeNode(child.keyl)

                    #descendants:
                    if (child.childml != None): #is instance
                        T.childmr.childl = child.childl

                    if (child.childl != None):
                        T.childmr.childr = child.childml
                else:
                    print("error - mid right child full")
