import { NewsArticle } from "./NewsArticle"

export interface ApiResponse{
    result : {
        response : string,
        newsCount : number,
        skipped : number
    },
    list : NewsArticle[]
}