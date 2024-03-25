export interface NewsArticle {
    Title: string;
    Source: string;
    Url: string;
    PublishedOn: string;
    Description: string;
    Language: string;
    Image: string;
    SourceNationality: string;
    TitleSentiment: {
        sentiment: string;
        score: number;
    };
    Summary: string;
    Countries: string[];
    Categories: {
        label: string;
        IPTCCode: string;
    };
}