import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Link from '@mui/material/Link';

export default function Home() {
  return (
    <Box sx={{ textAlign: 'center', mt: 8, px: 4 }}>

      <Typography variant="h4" component="h1" gutterBottom>
        Machine Learning at SJSU (ML@SJSU)
      </Typography>
      <Typography variant="h5" component="h2" gutterBottom sx={{ mb: 4 }}>
        Driving Innovation with Data
      </Typography>

      {/* Overview */}
      <Box sx={{ textAlign: 'left', maxWidth: 800, mx: 'auto', mb: 4 }}>
        <Typography variant="body1" paragraph>
          ML@SJSU is a student-run community of machine learning enthusiasts at San José State University. Our mission is to create an open, supportive environment where students can learn, teach, and explore machine learning together. We bridge the gap between theory and practice by helping members apply classroom knowledge to real-world projects and innovative solutions. We also strive to promote diversity in the field and tackle problems that are relevant to our community.
        </Typography>
      </Box>

      {/* What We Do */}
      <Box sx={{ textAlign: 'left', maxWidth: 800, mx: 'auto', mb: 4 }}>
        <Typography variant="h6" component="h3" gutterBottom>
          What We Do
        </Typography>
        <Typography variant="body1" paragraph>
          We host a variety of events and programs to spark growth in machine learning. Regular club meetings feature hands-on <strong>workshops</strong>, collaborative <strong>project sessions</strong>, guest <strong>speaker talks</strong>, and research <strong>paper discussions</strong>. These activities span a wide range of ML topics – from computer vision and neural networks to reinforcement learning and generative AI – allowing members to explore different interests within the field. By working on projects and attending events, members gain practical experience, build their skills, and connect with peers and mentors.
        </Typography>
      </Box>

      {/* Get Involved */}
      <Box sx={{ textAlign: 'left', maxWidth: 800, mx: 'auto', mb: 4 }}>
        <Typography variant="h6" component="h3" gutterBottom>
          Get Involved
        </Typography>
        <Typography variant="body1" paragraph>
          Whether you’re new to machine learning or an experienced pro, you are welcome at ML@SJSU. We meet every <strong>Friday from 10:00 AM – 12:00 PM in BBC 326</strong> – feel free to drop by and say hello! To stay up-to-date outside of meetings, join our online community on{' '}
          <Link href="https://discord.gg/Uvgcxya2r5" target="_blank" rel="noopener">
            Discord
          </Link>{' '}
          for announcements, resources, and project discussions.
        </Typography>
        <Typography variant="body1" paragraph>
          If you’re excited to dive deeper, you can also{' '}
          <Link href="https://mlatsjsu.com/about#get-involved" target="_blank" rel="noopener">
            become an official member
          </Link>{' '}
          of ML@SJSU to join project teams and take on leadership roles. And don’t forget to follow us on{' '}
          <Link href="https://www.instagram.com/ml.sjsu" target="_blank" rel="noopener">
            Instagram
          </Link>,{' '}
          <Link href="https://www.linkedin.com/company/mlatsjsu" target="_blank" rel="noopener">
            LinkedIn
          </Link>, and{' '}
          <Link href="https://www.youtube.com/channel/UCmC0mWgwdG9J8F7X1xjAcGg" target="_blank" rel="noopener">
            YouTube
          </Link>{' '}
          for the latest news and highlights. We look forward to learning and innovating together!
        </Typography>
      </Box>
    </Box>
  );
}
