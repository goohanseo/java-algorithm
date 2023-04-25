//int findMazePath(int x,int y){
//    if (x<0 || y<0 || x>=N || y>=N || maze[x][y] != PATHWAY_COLOUR)
//        return 0;
//    else if (x==N-1 && y==N-1){
//        maze[x][y] = PATH_COLOUR;
//        return 1;
//        }
//    maze[x][y] = PATH_COLOUR;
//    if (findMazePath(x-1,y) || findMazePath(x,y+1))
//        || findMazePath(x+1,y) || findMazePath(x,y-1)){
//    count = (findMazePath(x-1,y) || findMazePath(x,y+1))
//        || findMazePath(x+1,y) || findMazePath(x,y-1)
//    return count;
//        }
//        maze[x][y] = BLOCKED_COLOUR;
//    return false;
//        }