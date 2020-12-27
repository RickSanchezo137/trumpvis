import itertools
import json
import re

from flask import Flask
from flask import request
from jieba.analyse import *
from textblob import TextBlob

import tfidf


app = Flask(__name__)


class GetKeywords:
    @staticmethod
    def get_keywords(text):
        return tfidf.process(text)


class Clean:
    @staticmethod
    def clean(text):
        table = {ord(f): ord(t) for f, t in zip(
            u'，’“。！？【】（）％＃＠＆１２３４５６７８９０',
            u',\'".!?[]()%#@&1234567890')}
        text = text.translate(table)

        urls = re.compile(r'http://[a-zA-Z0-9.?/&=:]*', re.S)
        text = urls.sub("", text)
        urls = re.compile(r'https://[a-zA-Z0-9.?/&=:]*', re.S)
        text = urls.sub("", text)
        tags = re.compile(r'@ [a-zA-Z0-9.?/&=:]*', re.S)
        text = tags.sub("", text)
        tags = re.compile(r'# [a-zA-Z0-9.?/&=:]*', re.S)
        text = tags.sub("", text)
        pics = re.compile(r'pic[a-zA-Z0-9.?/&=:]*', re.S)
        text = pics.sub("", text)
        symbols = re.compile(r'[~`@#^*()_+={}\[\]|\\/][a-zA-Z0-9.?/&=:]*', re.S)
        text = symbols.sub("", text)
        elses = re.compile(r'-', re.S)
        text = elses.sub("", text)
        elses = re.compile(r'--', re.S)
        text = elses.sub("", text)

        # spelling correction
        text = re.sub(r"ph\.d", "phd", text)
        text = re.sub(r"PhD", "phd", text)
        text = re.sub(r" e g ", " eg ", text)
        text = re.sub(r" fb ", " facebook ", text)
        text = re.sub(r"facebooks", " facebook ", text)
        text = re.sub(r"facebooking", " facebook ", text)
        text = re.sub(r" usa ", " america ", text)
        text = re.sub(r" us ", " america ", text)
        text = re.sub(r" u s ", " america ", text)
        text = re.sub(r" U\.S\. ", " america ", text)
        text = re.sub(r" US ", " america ", text)
        text = re.sub(r" American ", " america ", text)
        text = re.sub(r" America ", " america ", text)
        text = re.sub(r" mbp ", " macbook-pro ", text)
        text = re.sub(r" mac ", " macbook ", text)
        text = re.sub(r"macbook pro", "macbook-pro", text)
        text = re.sub(r"macbook-pros", "macbook-pro", text)
        text = re.sub(r" 1 ", " one ", text)
        text = re.sub(r" 2 ", " two ", text)
        text = re.sub(r" 3 ", " three ", text)
        text = re.sub(r" 4 ", " four ", text)
        text = re.sub(r" 5 ", " five ", text)
        text = re.sub(r" 6 ", " six ", text)
        text = re.sub(r" 7 ", " seven ", text)
        text = re.sub(r" 8 ", " eight ", text)
        text = re.sub(r" 9 ", " nine ", text)
        text = re.sub(r"googling", " google ", text)
        text = re.sub(r"googled", " google ", text)
        text = re.sub(r"googleable", " google ", text)
        text = re.sub(r"googles", " google ", text)
        text = re.sub(r"dollars", " dollar ", text)

        text = re.sub(r"can\'t", "can not", text)
        text = re.sub(r"cannot", "can not ", text)
        text = re.sub(r"what\'s", "what is", text)
        text = re.sub(r"What\'s", "what is", text)
        text = re.sub(r"let\'s", "let us", text)
        text = re.sub(r"Let\'s", "let us", text)
        text = re.sub(r"\'s", " is", text)
        text = re.sub(r"\'ve ", " have ", text)
        text = re.sub(r"n\'t", " not ", text)
        text = re.sub(r"i\'m", "i am ", text)
        text = re.sub(r"I\'m", "i am ", text)
        text = re.sub(r"\'re", " are ", text)
        text = re.sub(r"\'d", " would ", text)
        text = re.sub(r"\'ll", " will ", text)
        text = re.sub(r" e mail ", " email ", text)
        text = re.sub(r" e - mail ", " email ", text)
        text = re.sub(r" e-mail ", " email ", text)

        # symbol replacement
        text = re.sub(r"&", " and ", text)
        text = re.sub(r"\|", " or ", text)
        text = re.sub(r"=", " equal ", text)
        text = re.sub(r"\+", " plus ", text)
        text = re.sub(r"\$", " dollar ", text)

        # remove extra space
        text = ' '.join(text.split())
        text = ''.join(''.join(s)[:2] for _, s in itertools.groupby(text))

        return text


@app.route('/keywords', methods=['GET', 'POST'])
def keywords():
    if request.method == 'POST':
        textlist = []
        data = str(request.get_data(), encoding='utf-8')
        texts = json.loads(data)
        for text in texts:
            textlist.append(text)
        results = GetKeywords.get_keywords(textlist)
        return results.__str__()


@app.route('/emotion', methods=['GET', 'POST'])
def emotion():
    if request.method == 'POST':
        text = str(request.get_data(), encoding='utf-8')
        blob = TextBlob(text)
        return str(blob.sentiment)


@app.route('/clean', methods=['GET', 'POST'])
def clean():
    if request.method == 'POST':
        text = str(request.get_data(), encoding='utf-8')
        text = Clean.clean(text)
        text = text.lower()
        return str(text)


if __name__ == '__main__':
    app.run(debug=True)
