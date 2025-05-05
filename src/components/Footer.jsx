import React from 'react';
import { Link } from 'react-router-dom';
import { BookOpen } from 'lucide-react';

function Footer() {
  return (
    <footer className="border-t bg-gray-50">
      <div className="container flex flex-col gap-2 py-6 px-4 md:px-6">
        <div className="flex flex-col gap-2 md:flex-row md:items-center md:justify-between">
          <div className="flex items-center gap-2">
            <BookOpen className="h-5 w-5 text-emerald-600" />
            <span className="text-lg font-semibold">ML@SJSU</span>
          </div>
          <nav className="flex gap-4 md:gap-6">
            <Link to="#" className="text-xs hover:underline underline-offset-4">
              Terms
            </Link>
            <Link to="#" className="text-xs hover:underline underline-offset-4">
              Privacy
            </Link>
            <Link to="#" className="text-xs hover:underline underline-offset-4">
              Contact
            </Link>
          </nav>
        </div>
        <div className="text-xs text-gray-500">&copy; {new Date().getFullYear()} ML@SJSU. All rights reserved.</div>
      </div>
    </footer>
  );
}

export default Footer;
