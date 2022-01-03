from urllib.request import urlopen, Request, urlretrieve
from fake_useragent import UserAgent
import json
import requests
import re
from urllib.error import URLError, HTTPError
import sqlite3


useragent = UserAgent()

baseUrl1 = 'https://recipe.bom.co.kr/recipe/'
baseUrl2 = '/detail'

def CrawlingBetweenRanges(startRecipeId, endRecipeId):
    for i in range(startRecipeId, endRecipeId):
        print("%d번째 요리:"%i)
        PageCrawler(str(i))

def PageCrawler(recipeUrl):
    url0 = baseUrl1 + recipeUrl + baseUrl2

    con = sqlite3.connect("cookforpet.db", isolation_level=None)
    cur = con.cursor()

    headers = {
        'referer' : url0,
        'User-Agent' : useragent.chrome
    }
    url = 'https://recipe.bom.co.kr/api/v1/post?embeds=user%2Ccate_detail%2Cop_function%2Cop_difficulty&filters=status=1,id='+recipeUrl

    response = urlopen(Request(url, headers=headers)).read().decode('utf-8')

    try:
        # 레시피 코드
        recipe_code = json.loads(response)['result'][0]['id']
        # 레시피 이름
        recipe_name = json.loads(response)['result'][0]['title']
        # 레시피 요약문
        recipe_sum = json.loads(response)['result'][0]['desc']
        # 반려동물 형태
        type = json.loads(response)['result'][0]['cate_detail']['title']
        # 조리시간
        res = json.loads(response)['result'][0]['cooking_time']
        if res==52:
            cook_time = "10분 이내"
        elif res==53:
            cook_time = "20분 이내"
        elif res==54:
            cook_time = "30분 이내"
        elif res==55:
            cook_time = "60분 이내"
        elif res==56:
            cook_time = "90분 이내"
        elif res==57:
            cook_time = "2시간 이상"
        # 보관 기한
        tip = json.loads(response)['result'][0]['content']
        # 대표사진 url
        res = json.loads(response)['result'][0]['cooking_representation']
        found = re.findall('\"images\":\"(.+?)\"',res)
        for i in range(len(found)):
            img_main = "https://recipe.bom.co.kr/uploads/"+found[i]
        # 기대효과
        effect = json.loads(response)['result'][0]['op_function']['title']
        # 좋아요 수

        insert_recipe = """insert into recipe values (?,?,?,?,?,?,?,?,?);"""
        cur.execute(insert_recipe, (recipe_code, recipe_name, recipe_sum,
        type, cook_time, tip, img_main, effect, 0))

        con.close()

    except HTTPError as e:
        err = e.read()
        code = e.getcode()
        print(code)
    except(IndexError):
        print("레시피가 없습니다.")
        return
    
if __name__ == '__main__':
    CrawlingBetweenRanges(242,1026)
