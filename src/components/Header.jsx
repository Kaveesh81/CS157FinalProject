import React from 'react';
import { Link } from 'react-router-dom';
import { Button } from './ui/button';
import { BookOpen } from 'lucide-react';

function Header() {
  return (
    <header className="bg-white border-b sticky top-0 z-10">
      <div className="container flex h-16 items-center justify-between px-4 md:px-6">
        <div className="flex items-center gap-2">
          <BookOpen className="h-6 w-6 text-emerald-600" />
          <Link to="/" className="text-xl font-bold">
            ML@SJSU
          </Link>
        </div>
        <nav className="hidden md:flex gap-6">
          <Link to="/projects" className="text-sm font-medium hover:underline underline-offset-4">
            Projects
          </Link>
          <Link to="/members" className="text-sm font-medium hover:underline underline-offset-4">
            Members
          </Link>
          <Link to="/showcase" className="text-sm font-medium hover:underline underline-offset-4">
            Showcase
          </Link>
          <Link to="/about" className="text-sm font-medium hover:underline underline-offset-4">
            About
          </Link>
        </nav>
        <div className="flex items-center gap-4">
          <Link to="/login">
            <Button variant="outline" size="sm">
              Log In
            </Button>
          </Link>
          <Link to="/signup">
            <Button size="sm" className="bg-emerald-600 hover:bg-emerald-700">
              Sign Up
            </Button>
          </Link>
        </div>
      </div>
    </header>
  );
}

export default Header;
