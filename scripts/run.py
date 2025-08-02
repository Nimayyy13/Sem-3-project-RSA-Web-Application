#!/usr/bin/env python3
"""
RSA Application Launcher
Main script to start both frontend and backend servers
"""

import subprocess
import sys
import os
import time
import threading
from pathlib import Path

def start_backend():
    """Start the backend server"""
    print("🚀 Starting RSA Backend Server...")
    backend_dir = Path(__file__).parent / "backend"
    os.chdir(backend_dir)
    
    try:
        subprocess.run([sys.executable, "server.py"], check=True)
    except KeyboardInterrupt:
        print("\n🛑 Backend server stopped")
    except Exception as e:
        print(f"❌ Backend server error: {e}")

def start_frontend():
    """Start the frontend server"""
    print("🌐 Starting RSA Frontend Server...")
    frontend_dir = Path(__file__).parent / "frontend"
    os.chdir(frontend_dir)
    
    try:
        subprocess.run([sys.executable, "start_server.py"], check=True)
    except KeyboardInterrupt:
        print("\n🛑 Frontend server stopped")
    except Exception as e:
        print(f"❌ Frontend server error: {e}")

def main():
    print("=" * 60)
    print("🚗 Road Side Assistance (RSA) Application Launcher")
    print("=" * 60)
    
    print("\n📋 Available options:")
    print("1. Start Backend Server (API)")
    print("2. Start Frontend Server (Web Interface)")
    print("3. Start Both Servers")
    print("4. Setup Database")
    print("5. Exit")
    
    while True:
        try:
            choice = input("\n🎯 Enter your choice (1-5): ").strip()
            
            if choice == "1":
                print("\n🔧 Starting Backend Server...")
                start_backend()
                break
                
            elif choice == "2":
                print("\n🌐 Starting Frontend Server...")
                start_frontend()
                break
                
            elif choice == "3":
                print("\n🚀 Starting Both Servers...")
                print("📱 Backend will be available at: http://localhost:5000")
                print("🌐 Frontend will be available at: http://localhost:8000")
                print("\n⏹️  Press Ctrl+C to stop both servers")
                
                # Start backend in a separate thread
                backend_thread = threading.Thread(target=start_backend)
                backend_thread.daemon = True
                backend_thread.start()
                
                # Wait a moment for backend to start
                time.sleep(2)
                
                # Start frontend
                start_frontend()
                break
                
            elif choice == "4":
                print("\n🗄️  Setting up database...")
                backend_dir = Path(__file__).parent / "backend"
                os.chdir(backend_dir)
                subprocess.run([sys.executable, "setup.py"], check=True)
                print("✅ Database setup completed!")
                break
                
            elif choice == "5":
                print("\n👋 Goodbye!")
                break
                
            else:
                print("❌ Invalid choice. Please enter 1-5.")
                
        except KeyboardInterrupt:
            print("\n👋 Goodbye!")
            break
        except Exception as e:
            print(f"❌ Error: {e}")

if __name__ == "__main__":
    main() 