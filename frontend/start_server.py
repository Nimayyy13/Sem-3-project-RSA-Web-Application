#!/usr/bin/env python3
"""
Simple HTTP Server for RSA Application
Run this script to start a local web server for the RSA application.
"""

import http.server
import socketserver
import os
import sys
from pathlib import Path

def start_server(port=8000):
    """Start a simple HTTP server on the specified port."""
    
    # Change to the directory containing this script
    script_dir = Path(__file__).parent
    os.chdir(script_dir)
    
    # Create server
    handler = http.server.SimpleHTTPRequestHandler
    
    try:
        with socketserver.TCPServer(("", port), handler) as httpd:
            print(f"🚀 RSA Application Server Started!")
            print(f"📱 Open your browser and navigate to:")
            print(f"   http://localhost:{port}")
            print(f"📁 Serving files from: {os.getcwd()}")
            print(f"⏹️  Press Ctrl+C to stop the server")
            print("-" * 50)
            
            # List available files
            print("📋 Available files:")
            for file in os.listdir('.'):
                if file.endswith(('.html', '.css', '.js')):
                    print(f"   - {file}")
            print("-" * 50)
            
            httpd.serve_forever()
            
    except KeyboardInterrupt:
        print("\n🛑 Server stopped by user")
    except OSError as e:
        if e.errno == 48:  # Address already in use
            print(f"❌ Port {port} is already in use.")
            print(f"💡 Try using a different port: python start_server.py {port + 1}")
        else:
            print(f"❌ Error starting server: {e}")
    except Exception as e:
        print(f"❌ Unexpected error: {e}")

if __name__ == "__main__":
    # Get port from command line argument or use default
    port = 8000
    if len(sys.argv) > 1:
        try:
            port = int(sys.argv[1])
        except ValueError:
            print("❌ Invalid port number. Using default port 8000.")
    
    print("=" * 50)
    print("🛣️  Road Side Assistance (RSA) Web Application")
    print("=" * 50)
    
    start_server(port) 