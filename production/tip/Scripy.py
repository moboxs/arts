# -*- coding:utf-8 -*


def content(html):
    str = '<article class="article-content">'
    content = html.partition(str)[2]
    str1 = '<div class="article-social">'
    content = html.partition(str1)[0]
    return content

