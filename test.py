import os
import re

webhook_pattern = re.compile(r"https:\/\/discord(?:app)?\.com\/api\/webhooks\/[^\s\"']+", re.IGNORECASE)
ip_pattern = re.compile(r"\b(?:\d{1,3}\.){3}\d{1,3}\b")
url_pattern = re.compile(r"https?:\/\/[^\s\"']+", re.IGNORECASE)

def scan_file(file_path):
    try:
        with open(file_path, 'r', encoding='utf-8', errors='ignore') as f:
            content = f.read()

        webhooks = webhook_pattern.findall(content)
        ips = ip_pattern.findall(content)
        urls = url_pattern.findall(content)

        if webhooks or ips or urls:
            print(f"\nSuspicious content in: {file_path}")
            if webhooks:
                for wh in webhooks:
                    print(f"  [Webhook] {wh}")
            if ips:
                for ip in ips:
                    print(f"  [IP]      {ip}")
            if urls:
                for url in urls:
                    print(f"  [URL]     {url}")

    except Exception as e:
        print(f" Could not read file: {file_path} — {e}")

def scan_directory(start_folder='.'):
    print("Scanning for Discord webhooks, IPs, and links...")
    for root, dirs, files in os.walk(start_folder):
        for file in files:
            scan_file(os.path.join(root, file))

scan_directory()