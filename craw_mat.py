from urllib.request import urlopen, Request
from fake_useragent import UserAgent
import json
import re
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

    conn = sqlite3.connect('craw_prac.db',isolation_level=None)
    c = conn.cursor()

    headers = {
        'referer' : url0,
        'User-Agent' : useragent.chrome
    }
    url = 'https://recipe.bom.co.kr/api/v1/post?embeds=user%2Ccate_detail%2Cop_function%2Cop_difficulty&filters=status=1,id='+recipeUrl

    response = urlopen(Request(url, headers=headers)).read().decode('utf-8')

    try:
        res = json.loads(response)['result'][0]['materials']
        mat_name = re.findall('name\":\"(.+?)\"',res)

        try:
            mat_qtt1 = re.findall('quantity\":\"(.+?)\"',res)
            mat_qtt2 = re.findall('unit\":\"(.+?)\"',res)
        except(IndexError):
            return None

        sql = """INSERT INTO materials(recipe_code,mat_num,mat_name,mat_qtt) VALUES (?,?,?,?)"""
                
        for i in range(len(mat_name)):
            recipe_code = int(recipeUrl)
            mat_num = i+1
                                          
            tmp = ""
            
            if mat_qtt2[i]=='60':
                tmp = "g"
            elif mat_qtt2[i]=='61':
                tmp = "kg"
            elif mat_qtt2[i]=='62':
                tmp = "ml"
            elif mat_qtt2[i]=='63':
                tmp = "l"
            elif mat_qtt2[i]=='64':
                tmp = "큰스푼"
            elif mat_qtt2[i]=='65':
                tmp = "작은스푼"
            elif mat_qtt2[i]=='66':
                tmp = "cup"
            elif mat_qtt2[i]=='67':
                tmp = "tbsp"
            elif mat_qtt2[i]=='68':
                tmp = "number"
            else:
                tmp = mat_qtt2[i]

            mat_qtt = mat_qtt1[i] +" "+ tmp
            
            c.execute(sql,(recipe_code, mat_num, mat_name[i].encode('utf-8').decode('unicode_escape'), mat_qtt))

        conn.close()

        
    except(IndexError):
        print("레시피가 없습니다.")
        return
    
if __name__ == '__main__':
    CrawlingBetweenRanges(181,1026)
