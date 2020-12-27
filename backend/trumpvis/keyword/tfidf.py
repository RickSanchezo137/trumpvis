import nltk
import math
import string
from nltk.corpus import stopwords
from collections import Counter
from nltk.stem.porter import *

text1 = "Be sure to tune in and watch Donald Trump on Late Night with David Letterman as he presents the Top Ten List " \
        "tonight! ,Donald Trump will be appearing on The View tomorrow morning to discuss Celebrity Apprentice and " \
        "his new book Think Like A Champion! ,Donald Trump reads Top Ten Financial Tips on Late Show with David " \
        "Letterman: Very funny! ,New Blog Post: Celebrity Apprentice Finale and Lessons " \
        "Learned Along the Way: ,My persona will never be that of a wallflower I’d rather " \
        "build walls than cling to them --Donald J. Trump ,Miss USA Tara Conner will not be fired - I've always been " \
        "a believer in second chances. says Donald Trump ,Listen to an interview with Donald Trump discussing his new " \
        "book, Think Like A Champion ,Strive for wholeness and keep your sense of wonder " \
        "intact. Donald Trump  ,Enter the Think Like A Champion signed book and " \
        "keychain contest "

text2 = "Check out Donald Trump's new iGoogle Showcase page ,If you don't have problems, " \
        "you're pretending or you don't run your own business. Donald Trump ," \
        "Last week to enter the Think Like A Champion signed book and keychain contest: " \
        " ,Today is Donald Trump's Birthday! Send him your Birthday " \
        "wishes here ,Thanks to all for your thoughtful birthday wishes Donald " \
        "Trump ,RE: FB Vanity URLs: SF Chronicle David Beckham was one of the first, along with Britney Spears & " \
        "Donald Trump. Wishing a Happy Father's Day to all the Dad's out there YOU are " \
        "a champion today and everyday! ,“Expand your life every day.” Donald Trump " \
        ",Donald Trump commercial-free WWE Raw does big rating " \
        ",RE: Michael Jackson: He was a great friend and a spectacular entertainer. It is "

text3 = "Here's to a safe and happy Independence Day for one and all Enjoy it! Donald Trump ,Be aware of " \
        "things that seem inexplicable because they can be a big step towards innovation. Donald Trump " \
        " ,Donald Trump backs 'Apprentice' Randal Pinkett for N.J. Lieutenant Governor: " \
        ",Congrats to winners from around the world who entered the Think Like A Champion " \
        "signed book keychain contest! ,Check out a list of Donald Trump's books for summer " \
        "reading at the Trump University Blog "


def get_tokens(text):
    # lowers = text.lower()

    remove_punctuation_map = dict((ord(char), None) for char in string.punctuation)
    no_punctuation = text.translate(remove_punctuation_map)
    tokens = nltk.word_tokenize(no_punctuation)
    return tokens


def stem_tokens(tokens, stemmer):
    stemmed = []
    for item in tokens:
        stemmed.append(stemmer.stem(item))
    return stemmed


def tf(word, count):
    return count[word] / sum(count.values())


def n_containing(word, count_list):
    return sum(1 for count in count_list if word in count)


def idf(word, count_list):
    return math.log(len(count_list) / (1 + n_containing(word, count_list)))


def tfidf(word, count, count_list):
    return tf(word, count) * idf(word, count_list)


def make_count(text):
    tokens = get_tokens(text)
    filtered = [w for w in tokens if w not in stopwords.words('english')]
    stemmer = PorterStemmer()  # 生成波特词干算法实例
    stemmed = stem_tokens(filtered, stemmer)
    count = Counter(stemmed)
    return count


def make_countlist(textlist):
    countlist = []
    for text in textlist:
        countlist.append(make_count(text))
    return countlist


def process(textlist):
    countlist = make_countlist(textlist)
    results = []
    for i, count in enumerate(countlist):
        # print("Top words in document {}".format(i + 1))
        scores = {word: tfidf(word, count, countlist) for word in count}
        sorted_words = sorted(scores.items(), key=lambda x: x[1], reverse=True)
        results += sorted_words
        # for word, score in sorted_words[:3]:
        #    print("\tWord: {}, TF-IDF: {}".format(word, round(score, 5)))
    return results


if __name__ == '__main__':
    process([text1, text2, text3])
